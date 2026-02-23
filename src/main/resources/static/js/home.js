// home.js - Düzeltilmiş Versiyon (Entity Hatası Giderildi)

let selectedSeat = null;
let currentRotaPlan = {}; 
let selectedVehicleType = "all"; 
let allFetchedData = []; 

// --- TAB DEĞİŞTİĞİNDE ÇALIŞAN FONKSİYON ---
window.updateTab = (radio) => {
    selectedVehicleType = radio.value;
    document.querySelectorAll('.tab-label').forEach(label => label.classList.remove('active'));
    radio.parentElement.classList.add('active');

    if(allFetchedData.length > 0) {
        renderSeferler();
    }
};

// --- LİSTELEME FONKSİYONU ---
function renderSeferler() {
    const resultsList = document.getElementById("results-list");
    const resultsSection = document.getElementById("results");

    resultsList.innerHTML = "";

    // 1. Türkçe karakter düzeltme fonksiyonu
    const normalize = (str) => {
        if(!str) return ""; 
        return str.toLowerCase()
                  .replace(/ç/g, 'c')
                  .replace(/ğ/g, 'g')
                  .replace(/ü/g, 'u')
                  .replace(/ş/g, 's')
                  .replace(/ö/g, 'o')
                  .replace(/ı/g, 'i');
    };

    // 2. Veriyi filtrele
    const filteredData = allFetchedData.filter(plan => {
        if (selectedVehicleType === "all") return true;

        // ✅ KRİTİK DÜZELTME: Veri yolunu 'ulasimTuru' üzerinden alıyoruz
        let gelenAracTipi = "Araç";
        if (plan.arac && plan.arac.ulasimTuru && plan.arac.ulasimTuru.aracTipi) {
            gelenAracTipi = plan.arac.ulasimTuru.aracTipi;
        } else if (plan.arac && plan.arac.aracTipi) {
            gelenAracTipi = plan.arac.aracTipi; // Yedek kontrol
        }
        
        const dbTip = normalize(gelenAracTipi);
        const secilenTip = normalize(selectedVehicleType);

        return dbTip.includes(secilenTip);
    });

    // Sonuç yoksa uyarı göster
    if (filteredData.length === 0) {
        resultsList.innerHTML = `
            <div class="no-result" style="text-align:center; color:white; padding:20px;">
                <p>Aradığınız kriterlere uygun sefer bulunamadı.</p>
            </div>`;
        resultsSection.classList.remove("hidden");
        return;
    }

    // Listeleme
    filteredData.forEach(plan => {
        const firmaAd = plan.firma ? plan.firma.firmaAdi : (plan.arac && plan.arac.firma ? plan.arac.firma.firmaAdi : "Firma");
		const kalkis = plan.rota?.kalkisKonum?.sehir?.sehirAdi || "?";
		const varis  = plan.rota?.varisKonum?.sehir?.sehirAdi  || "?";

        const saat = plan.seferSaati || "";
        const sure = plan.tahminiSure || "";
        const fiyat = plan.biletFiyati;

        // ✅ KRİTİK DÜZELTME 2: Burada da doğru yolu kullanıyoruz
        let aracTipi = "Araç";
        if (plan.arac && plan.arac.ulasimTuru && plan.arac.ulasimTuru.aracTipi) {
            aracTipi = plan.arac.ulasimTuru.aracTipi;
        }

        const rotaPlanId = plan.rotaPlanId; 
        const realRotaId = plan.rota ? plan.rota.rotaId : 0; 
        const aracId = plan.arac ? plan.arac.aracId : 0;
        
        const firmaId = plan.arac && plan.arac.firma 
            ? plan.arac.firma.firmaId 
            : (plan.firma ? plan.firma.firmaId : 0);

        // İkon belirleme
        let iconClass = "fa-bus";
        const normalizedType = normalize(aracTipi);
        if(normalizedType.includes("ucak")) iconClass = "fa-plane";
        else if(normalizedType.includes("vapur") || normalizedType.includes("gemi")) iconClass = "fa-ship";

        resultsList.innerHTML += `
        <div class="ticket-card animate__animated animate__fadeInUp">
            
            <div class="ticket-left">
                <div class="company-name"><i class="fas ${iconClass}"></i> ${firmaAd}</div>
                <span class="ticket-type">${aracTipi}</span>
            </div>

            <div class="ticket-center">
                <div class="route-group"><span class="time">${saat.substring(0,5)}</span><span class="city">${kalkis}</span></div>
                <div class="route-line">
                    <span style="position:absolute; top:-25px; font-size:0.8rem; color:#aaa; white-space:nowrap;"><i class="far fa-clock"></i> ${sure}</span>
                    <i class="fa-solid fa-arrow-right"></i>
                </div>
                <div class="route-group"><span class="time" style="opacity:0.5; font-size:1.5rem">--:--</span><span class="city">${varis}</span></div>
            </div>

            <div class="ticket-right">
                <div class="price-row" style="display:flex; align-items:center; gap:10px; margin-bottom:10px;">
                    
                    <button class="btn-fav" onclick="toggleFavorite(${realRotaId}, ${firmaId})" title="Favorilere Ekle">
                        <i class="fa-regular fa-heart"></i>
                    </button>

                    <div class="price-tag">${fiyat} <span>₺</span></div>
                </div>
                
                <button class="btn-select-seat" 
                    onclick="openSeatModal(${rotaPlanId}, ${aracId}, ${firmaId}, '${firmaAd}', ${fiyat}, '${kalkis} - ${varis}')">
                    Koltuk Seç <i class="fa-solid fa-chevron-right"></i>
                </button>
            </div>

        </div>
        `;
    });
    resultsSection.classList.remove("hidden");
}

document.addEventListener("DOMContentLoaded", () => {
  const searchBtn = document.getElementById("searchBtn");
  
  if (searchBtn) {
    searchBtn.addEventListener("click", async () => {
      const from = document.getElementById("kalkisInput").value;
      const to = document.getElementById("varisInput").value;
      const date = document.getElementById("tarihInput").value;

      if (!from || !to) {
        alert("Lütfen en az kalkış ve varış yerini seçiniz.");
        return;
      }

      searchBtn.innerHTML = '<i class="fa-solid fa-spinner fa-spin"></i> Aranıyor...';

      try {
        // Backend url
        const url = `/api/rota-plan/sefer-ara?kalkis=${from}&varis=${to}&tip=${selectedVehicleType || 'all'}&tarih=${date || ''}`;
        
        const res = await fetch(url);
        if (!res.ok) throw new Error("Arama hatası");
        
        allFetchedData = await res.json();
        
        searchBtn.innerHTML = '<i class="fa-solid fa-magnifying-glass"></i> Sefer Bul';
        renderSeferler();

      } catch (err) {
        console.error(err);
        searchBtn.innerHTML = '<i class="fa-solid fa-magnifying-glass"></i> Sefer Bul';
        alert("Seferler getirilirken bir hata oluştu.");
      }
    });
  }

  const reserveBtn = document.getElementById("reserveBtn");
  if (reserveBtn) reserveBtn.addEventListener("click", () => islemYap("Rezerve"));
  const buyBtn = document.getElementById("buyBtn");
  if (buyBtn) buyBtn.addEventListener("click", () => islemYap("SatinAl"));
});

// --- KOLTUK SEÇİM MODALI ---
window.openSeatModal = async (rotaPlanId, aracId, firmaId, firmaAd, fiyat, guzergah) => {
  const modal = document.getElementById("seatModal");
  const grid = document.getElementById("seats-grid");
  
  document.getElementById("modal-route-info").innerText = `${firmaAd} | ${guzergah}`;
  document.getElementById("total-price").innerText = "0";

  // Global değişkene ata
  currentRotaPlan = { rotaPlanId, aracId, firmaId, fiyat };
  selectedSeat = null;

  grid.innerHTML = '<div class="loading">Koltuklar yükleniyor...</div>';
  modal.classList.add("active");
  modal.classList.remove("hidden");

  try {
    const koltukRes = await fetch(`/koltuklar/${rotaPlanId}/koltuklar`);

    if (!koltukRes.ok) {
      throw new Error(`Koltuk endpoint hatası: ${koltukRes.status}`);
    }

    const seats = await koltukRes.json();
    const dolulukRes = await fetch(`/api/rezervasyonlar/rota-plan/${rotaPlanId}`);

    if (!dolulukRes.ok) {
      throw new Error(`Rezervasyon endpoint hatası: ${dolulukRes.status}`);
    }

    const rezervasyonlar = await dolulukRes.json();
    const rezervasyonListesi = Array.isArray(rezervasyonlar) ? rezervasyonlar : [];

    const rezerveKoltukIds = rezervasyonListesi
      .filter(r => r && r.durum === 'Beklemede' && r.koltuk && r.koltuk.koltukId)
      .map(r => r.koltuk.koltukId);

    const satilanKoltukIds = rezervasyonListesi
      .filter(r => r && r.durum === 'Biletlendi' && r.koltuk && r.koltuk.koltukId)
      .map(r => r.koltuk.koltukId);

    const seatListesi = Array.isArray(seats) ? seats : [];
    seatListesi.sort((a, b) => parseInt(a.koltukNo) - parseInt(b.koltukNo));

    grid.innerHTML = "";

    seatListesi.forEach(s => {
      const div = document.createElement("div");
      div.innerText = s.koltukNo;

      if (satilanKoltukIds.includes(s.koltukId)) {
        div.className = "seat full";
      } else if (rezerveKoltukIds.includes(s.koltukId)) {
        div.className = "seat reserved";
      } else {
        div.className = "seat empty";
        div.onclick = () => {
          document.querySelectorAll(".seat.selected").forEach(x => x.classList.remove("selected"));
          div.classList.add("selected");
          selectedSeat = s;
          document.getElementById("total-price").innerText = fiyat;
        };
      }

      grid.appendChild(div);
    });

  } catch (e) {
    grid.innerHTML = "Koltuk bilgisi alınamadı.";
    console.error("openSeatModal hata:", e);
  }
};

window.closeSeatModal = () => {
  document.getElementById("seatModal").classList.remove("active");
};

// --- İŞLEM YAP ---
async function islemYap(tip) {
    if (!selectedSeat) {
        alert("Lütfen bir koltuk seçiniz!");
        return;
    }
    
    const user = JSON.parse(localStorage.getItem("user"));
    if (!user) {
        alert("İşlem yapmak için giriş yapmalısınız.");
        window.location.href = "/giris";
        return;
    }

	const payload = {
	        rotaPlan: { 
	            rotaPlanId: currentRotaPlan.rotaPlanId 
	        },
	        koltuk: { koltukId: selectedSeat.koltukId },
	        
	        yolcu: { yolcuId: user.kullaniciId }, 
	        fiyat: currentRotaPlan.fiyat,
	        durum: tip === "SatinAl" ? "Biletlendi" : "Beklemede"
	    };
    if (tip === "SatinAl") {
        if (!confirm(`${currentRotaPlan.fiyat} TL ödeme alınacak. Onaylıyor musunuz?`)) return;
    }

    try {
        const res = await fetch("/api/rezervasyonlar", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(payload)
        });

        if (!res.ok) {
            const errData = await res.json().catch(() => ({}));
            throw new Error(errData.message || "İşlem hatası.");
        }

        const rezData = await res.json();

		if (tip === "SatinAl") {
		    alert("Biletiniz başarıyla oluşturuldu!");
		    window.location.href = "/ticket";
		} else {
		    alert("Rezervasyonunuz başarıyla oluşturuldu.");
		    window.location.href = "/reservations";
		}


    } catch (e) {
        console.error(e);
        alert("Hata: " + e.message);
    }
}