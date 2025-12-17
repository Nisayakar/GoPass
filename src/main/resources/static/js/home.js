let selectedSeat = null;
let currentRotaPlan = {}; // Artık tüm sefer bilgisini burada tutacağız

document.addEventListener("DOMContentLoaded", () => {
  const searchBtn = document.getElementById("searchBtn");
  const resultsList = document.getElementById("results-list");
  const resultsSection = document.getElementById("results");

  // --- 1. SEFER ARAMA ---
  if (searchBtn) {
    searchBtn.addEventListener("click", async () => {
      const from = document.getElementById("kalkisInput").value; // Input ID'lerine dikkat
      const to = document.getElementById("varisInput").value;
      const date = document.getElementById("tarihInput").value; // Tarih eklendi

      if (!from || !to) {
        alert("Lütfen en az kalkış ve varış yerini seçiniz.");
        return;
      }

      searchBtn.innerHTML = '<i class="fa-solid fa-spinner fa-spin"></i> Aranıyor...';

      try {
        // Backend'de RotaPlanController'a uygun endpoint
       const url = `/api/rota-plan/ara?kalkis=${from}&varis=${to}&tarih=${date || ''}`;
        
        const res = await fetch(url);
        
        if (!res.ok) throw new Error("Arama hatası");
        
        const data = await res.json();

        resultsList.innerHTML = "";
        searchBtn.innerHTML = "Otobüs Bileti Bul";

        if (data.length === 0) {
          resultsList.innerHTML = '<div class="no-result"><p>Aradığınız kriterlere uygun sefer bulunamadı.</p></div>';
          resultsSection.classList.remove("hidden");
          return;
        }

        // --- LİSTELEME ---
        data.forEach(plan => {
          // Backend'den gelen RotaPlan nesnesi
          const firmaAd = plan.firma.firmaAdi;
          const kalkis = plan.rota.kalkisKonum.sehir;
          const varis = plan.rota.varisKonum.sehir;
          const saat = plan.seferSaati; // Örn: 14:30:00
          const sure = plan.tahminiSure || "Belirsiz";
          const fiyat = plan.biletFiyati;
          const aracTipi = plan.arac.aracTipi || "Otobüs"; 
          
          // HTML Kart Yapısı
          resultsList.innerHTML += `
            <div class="sefer-card">
              <div class="firma-info">
                 <div class="firma-logo-placeholder">${firmaAd.substring(0,1)}</div>
                 <div>
                    <h3>${firmaAd}</h3>
                    <small>${aracTipi}</small>
                 </div>
              </div>

              <div class="zaman-bilgi">
                <div class="saat">${saat.substring(0,5)}</div>
                <div class="sure"><i class="far fa-clock"></i> ${sure}</div>
              </div>

              <div class="guzergah">
                 <span>${kalkis}</span> 
                 <i class="fas fa-arrow-right" style="color:#ccc; margin:0 10px;"></i> 
                 <span>${varis}</span>
              </div>

              <div class="fiyat-buton">
                 <div class="fiyat">${fiyat} ₺</div>
                 <div class="actions-row">
                    <button class="favori-btn" onclick="toggleFavorite(${plan.rotaPlanId})">
                        <i class="far fa-heart"></i>
                    </button>
                    <button class="btn-sec" 
                        onclick="openSeatModal(${plan.rotaPlanId}, ${plan.arac.aracId}, '${firmaAd}', ${fiyat}, '${kalkis} - ${varis}')">
                        Koltuk Seç
                    </button>
                 </div>
              </div>
            </div>
          `;
        });
        
        resultsSection.classList.remove("hidden");

      } catch (err) {
        console.error(err);
        searchBtn.innerHTML = "Otobüs Bileti Bul";
        alert("Seferler getirilirken bir hata oluştu.");
      }
    });
  }

  // --- 2. REZERVE ET & SATIN AL BUTONLARI ---
  // (Bu butonlar Modal'ın içindedir)
  
  const reserveBtn = document.getElementById("reserveBtn");
  if (reserveBtn) {
    reserveBtn.addEventListener("click", () => islemYap("Rezerve"));
  }

  const buyBtn = document.getElementById("buyBtn");
  if (buyBtn) {
    buyBtn.addEventListener("click", () => islemYap("SatinAl"));
  }
});

// --- KOLTUK SEÇİM MODALI ---
window.openSeatModal = async (rotaPlanId, aracId, firmaAd, fiyat, guzergah) => {
  const modal = document.getElementById("seatModal");
  const grid = document.getElementById("seats-grid");
  
  // Modal Bilgilerini Doldur
  document.getElementById("modal-route-info").innerText = `${firmaAd} | ${guzergah}`;
  document.getElementById("total-price").innerText = "0";

  // Global değişkene ata
  currentRotaPlan = { rotaPlanId, fiyat };
  selectedSeat = null;

  grid.innerHTML = '<div class="loading">Koltuklar yükleniyor...</div>';
  modal.classList.add("active");
  modal.classList.remove("hidden"); // CSS yapına göre değişebilir

  try {
    // 1. O araçtaki tüm koltukları çek
    // 2. O seferdeki (RotaPlan) dolu koltukları çek (Rezervasyon tablosundan)
    // Şimdilik basitlik adına sadece araç koltuklarını çekiyoruz, doluluk kontrolü backend'de yapılmalı veya ayrı endpoint lazım.
    // İdeal olan: /api/koltuklar/doluluk-durumu?rotaPlanId=... çağırmaktır.
    // Biz mevcut yapınla devam edelim:
    
    const res = await fetch(`/api/koltuklar/arac/${aracId}`);
    const seats = await res.json();
    
    // Koltukları numaraya göre sırala
    seats.sort((a, b) => parseInt(a.koltukNo) - parseInt(b.koltukNo));

    grid.innerHTML = "";
    
    // TODO: Bu RotaPlan için DOLU olan koltukları ayrıca fetch edip bu listede işaretlemek gerekir.
    // Şimdilik hepsi boş gibi davranıyoruz (Backend düzeltmesi gerekebilir).
    
    seats.forEach(s => {
       const div = document.createElement("div");
       div.className = "seat empty"; // Varsayılan boş
       div.innerText = s.koltukNo;
       
       div.onclick = () => {
           // Önceki seçimi kaldır
           document.querySelectorAll(".seat.selected").forEach(x => x.classList.remove("selected"));
           
           div.classList.add("selected");
           selectedSeat = s;
           document.getElementById("total-price").innerText = fiyat;
       };
       grid.appendChild(div);
    });

  } catch (e) {
    grid.innerHTML = "Koltuk bilgisi alınamadı.";
    console.log(e);
  }
};

window.closeSeatModal = () => {
  document.getElementById("seatModal").classList.remove("active");
};

// --- İŞLEM YAP (REZERVE VEYA SATIN AL) ---
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
        rotaPlan: { rotaPlanId: currentRotaPlan.rotaPlanId }, // ARTIK TEK ID GİDİYOR
        koltuk: { koltukId: selectedSeat.koltukId },
        kullanici: { kullaniciId: user.kullaniciId },
        fiyat: currentRotaPlan.fiyat,
        durum: tip === "SatinAl" ? "Biletlendi" : "Beklemede" // Direkt durumu belirle
    };

    if (tip === "SatinAl") {
        if (!confirm(`${currentRotaPlan.fiyat} TL ödeme alınacak. Onaylıyor musunuz?`)) return;
    }

    try {
        // 1. Rezervasyon Kaydı
        const res = await fetch("/api/rezervasyonlar", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(payload)
        });

        if (!res.ok) throw new Error("İşlem başarısız");

        const rezData = await res.json();

        // 2. Eğer Satın Alma ise Ödeme ve Bilet Tablolarını Doldur
        if (tip === "SatinAl") {
            // Ödeme Kaydı
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

            // Bilet Kaydı
            await fetch("/api/bilet", {
                 method: "POST",
                 headers: { "Content-Type": "application/json" },
                 body: JSON.stringify({
                     rezervasyon: { rezervasyonId: rezData.rezervasyonId },
                     kullanici: { kullaniciId: user.kullaniciId },
                     biletNo: "PNR-" + Math.floor(100000 + Math.random() * 900000),
                     olusturulmaTarihi: new Date().toISOString().split('T')[0]
                 })
            });
            
            alert("Biletiniz başarıyla oluşturuldu! İyi yolculuklar.");
            window.location.href = "/ticket"; // Biletlerim sayfası
        } else {
            alert("Rezervasyonunuz oluşturuldu. Lütfen süresi dolmadan satın alınız.");
            window.location.href = "/reservations"; // Rezervasyonlarım sayfası
        }

    } catch (e) {
        console.error(e);
        alert("Bir hata oluştu: " + e.message);
    }
}