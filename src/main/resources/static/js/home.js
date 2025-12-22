let selectedSeat = null;
let currentRotaPlan = {}; 
let selectedVehicleType = "all"; // Varsayılan filtre
let allFetchedData = []; // Çekilen sefer verilerini burada tutacağız

// --- TAB DEĞİŞTİĞİNDE ÇALIŞAN FONKSİYON ---
window.updateTab = (radio) => {
    selectedVehicleType = radio.value;
    
    // 1. Görsel olarak aktif butonu güncelle
    document.querySelectorAll('.tab-label').forEach(label => label.classList.remove('active'));
    radio.parentElement.classList.add('active');

    // 2. Eğer elimizde arama sonucu varsa listeyi hemen güncelle
    if(allFetchedData.length > 0) {
        renderSeferler();
    }
};

// --- LİSTELEME FONKSİYONU ---
function renderSeferler() {
    const resultsList = document.getElementById("results-list");
    const resultsSection = document.getElementById("results");

    resultsList.innerHTML = "";

    // 1. Filtreleme İşlemi
    const filteredData = allFetchedData.filter(plan => {
        // "Tümü" seçiliyse hepsini göster
        if (selectedVehicleType === "all") return true;
        
        // HATA ÖNLEYİCİ: Araç bilgisi veya tipi yoksa hata verme, o kaydı atla
        if (!plan.arac || !plan.arac.aracTipi) return false;
        
        // Araç tipini kontrol et (Küçük harfe çevirerek)
        return plan.arac.aracTipi.toLowerCase().includes(selectedVehicleType.toLowerCase());
    });

    // 2. Sonuç Yoksa Mesaj Göster
    if (filteredData.length === 0) {
        resultsList.innerHTML = `
            <div class="no-result">
                <p>Aradığınız kriterlere uygun <b>${selectedVehicleType === 'all' ? '' : selectedVehicleType}</b> seferi bulunamadı.</p>
            </div>`;
        resultsSection.classList.remove("hidden");
        return;
    }

    // 3. Kartları Oluştur ve Ekrana Bas
   // 3. Kartları Oluştur ve Ekrana Bas
    filteredData.forEach(plan => {
        // --- DEĞİŞKENLERİ HAZIRLAMA (AYNEN KORUNDU) ---
        const firmaAd = plan.firma ? plan.firma.firmaAdi : "Firma";
        const kalkis = plan.rota ? plan.rota.kalkisKonum.sehir : "?";
        const varis = plan.rota ? plan.rota.varisKonum.sehir : "?";
        const saat = plan.seferSaati || "";
        const sure = plan.tahminiSure || "";
        const fiyat = plan.biletFiyati;
        const aracTipi = (plan.arac && plan.arac.aracTipi) ? plan.arac.aracTipi : "Araç";
        
        // ID'leri güvenli şekilde al
        const rId = plan.id ? plan.id.rotaId : 0;
        const aId = plan.id ? plan.id.aracId : 0;
        const fId = plan.id ? plan.id.firmaId : 0;

        // İkon seçimi
        let iconClass = "fa-bus";
        if(aracTipi.toLowerCase().includes("uçak")) iconClass = "fa-plane";
        else if(aracTipi.toLowerCase().includes("vapur") || aracTipi.toLowerCase().includes("gemi")) iconClass = "fa-ship";

        // --- YENİ MODERN HTML TASARIMI BURADA ---
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
                    
                    <button class="btn-fav" onclick="toggleFavorite(${rId}, ${aId}, ${fId})" title="Favorilere Ekle">
                        <i class="fa-regular fa-heart"></i>
                    </button>

                    <div class="price-tag">${fiyat} <span>₺</span></div>
                </div>
                
                <button class="btn-select-seat" 
                    onclick="openSeatModal(${rId}, ${aId}, ${fId}, '${firmaAd}', ${fiyat}, '${kalkis} - ${varis}')">
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
  
  // --- ARAMA BUTONU ---
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
        const url = `/api/rota-plan/ara?kalkis=${from}&varis=${to}&tarih=${date || ''}`;
        
        const res = await fetch(url);
        if (!res.ok) throw new Error("Arama hatası");
        
        // Veriyi çekip global değişkene atıyoruz
        allFetchedData = await res.json();
        
        searchBtn.innerHTML = '<i class="fa-solid fa-magnifying-glass"></i> Sefer Bul';
        
        // Listeleme fonksiyonunu çağırıyoruz
        renderSeferler();

      } catch (err) {
        console.error(err);
        searchBtn.innerHTML = '<i class="fa-solid fa-magnifying-glass"></i> Sefer Bul';
        alert("Seferler getirilirken bir hata oluştu.");
      }
    });
  }

  // Buton dinleyicileri (Rezerve/Satın Al)
  const reserveBtn = document.getElementById("reserveBtn");
  if (reserveBtn) reserveBtn.addEventListener("click", () => islemYap("Rezerve"));
  const buyBtn = document.getElementById("buyBtn");
  if (buyBtn) buyBtn.addEventListener("click", () => islemYap("SatinAl"));
});

// --- KOLTUK SEÇİM MODALI ---
window.openSeatModal = async (rotaId, aracId, firmaId, firmaAd, fiyat, guzergah) => {
  const modal = document.getElementById("seatModal");
  const grid = document.getElementById("seats-grid");
  
  document.getElementById("modal-route-info").innerText = `${firmaAd} | ${guzergah}`;
  document.getElementById("total-price").innerText = "0";

  currentRotaPlan = { rotaId, aracId, firmaId, fiyat };
  selectedSeat = null;

  grid.innerHTML = '<div class="loading">Koltuklar yükleniyor...</div>';
  modal.classList.add("active");
  modal.classList.remove("hidden");

  try {
    const koltukRes = await fetch(`/api/koltuklar/arac/${aracId}`);
    const seats = await koltukRes.json();
    
    // Doğru Endpoint: 3 parametreli
    const dolulukRes = await fetch(`/api/rezervasyonlar/rota-plan/${rotaId}/${aracId}/${firmaId}`);
    const rezervasyonlar = await dolulukRes.json();
    
    const rezerveKoltukIds = rezervasyonlar.filter(r => r.durum === 'Beklemede').map(r => r.koltuk.koltukId);
    const satilanKoltukIds = rezervasyonlar.filter(r => r.durum === 'Biletlendi').map(r => r.koltuk.koltukId);

    seats.sort((a, b) => parseInt(a.koltukNo) - parseInt(b.koltukNo));

    grid.innerHTML = "";
    
    seats.forEach(s => {
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
    console.error(e);
  }
};

window.closeSeatModal = () => {
  document.getElementById("seatModal").classList.remove("active");
};

// --- İŞLEM YAP (Aynen korundu) ---
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
            id: {
                rotaId: currentRotaPlan.rotaId,
                aracId: currentRotaPlan.aracId,
                firmaId: currentRotaPlan.firmaId
            }
        },
        koltuk: { koltukId: selectedSeat.koltukId },
        kullanici: { kullaniciId: user.kullaniciId },
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
            await fetch("/api/odemeler", {
                method: "POST",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify({
                    rezervasyon: { rezervasyonId: rezData.rezervasyonId },
                    odemeMetodu: "Kredi Kartı",
                    odemeDurumu: "Tamamlandı",
                    fiyat: currentRotaPlan.fiyat
                })
            });

            await fetch("/api/bilet/satin-al", {
                 method: "POST",
                 headers: { "Content-Type": "application/json" },
                 body: JSON.stringify({
                     rezervasyon: { rezervasyonId: rezData.rezervasyonId },
                     biletNo: "PNR-" + Math.floor(100000 + Math.random() * 900000)
                 })
            });
            
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

