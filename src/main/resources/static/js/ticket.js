document.addEventListener("DOMContentLoaded", async () => {
    const list = document.getElementById("ticket-list");
    const user = JSON.parse(localStorage.getItem("user"));
  
    // 1. Giriş Kontrolü
    if (!user) {
      console.log("Kullanıcı giriş yapmamış, yönlendiriliyor...");
      location.href = "/giris";
      return;
    }
  
    // 2. Biletleri Çek
    try {
        console.log("Biletler çekiliyor... Kullanıcı ID:", user.kullaniciId);
        const res = await fetch(`/api/bilet/kullanici/${user.kullaniciId}`);
        
        if (!res.ok) throw new Error(`Sunucu Hatası: ${res.status}`);
        
        const data = await res.json();
        console.log("Gelen Bilet Verisi:", data); // Konsola veriyi yazdır
  
        // 3. Hiç Bilet Yoksa
        if (!data || data.length === 0) {
            list.innerHTML = `
                <div style="text-align:center; color:white; padding:40px;">
                    <h3>Henüz biletiniz bulunmuyor.</h3>
                    <p style="color:#aaa;">Yeni bir seyahat planlamaya ne dersin?</p>
                    <a href="/" style="display:inline-block; margin-top:15px; padding:10px 20px; background:#f1c40f; color:black; border-radius:10px; text-decoration:none; font-weight:bold;">Sefer Ara</a>
                </div>`;
            return;
        }
  
        list.innerHTML = ""; // Listeyi temizle
  
        // 4. Biletleri Listele (GÜVENLİ DÖNGÜ)
        data.forEach(b => {
            // Veri parçalarını güvenli şekilde alalım (Zincirleme kontrol)
            const rez = b.rezervasyon || {}; 
            const plan = rez.rotaPlan || {};
            const rota = plan.rota || {};
            
            // --- DÜZELTİLEN KISIM BAŞLANGIÇ ---
            // Firma bilgisi 'Arac' içindedir.
            const arac = plan.arac || {};
            const firma = arac.firma || {}; 
            // --- DÜZELTİLEN KISIM BİTİŞ ---

            const koltuk = rez.koltuk || {};
            
            // Değerleri hazırla (Yoksa varsayılan metin koy)
            const firmaAdi = firma.firmaAdi || "Firma Belirtilmemiş"; // Artık doğru çalışır
			const kalkis = rota.kalkisKonum?.sehir?.sehirAdi || "Kalkış?";
			const varis  = rota.varisKonum?.sehir?.sehirAdi  || "Varış?";

            
            const tarih = plan.seferTarihi || "Tarih Yok";
            const saat = plan.seferSaati || "--:--";
            const koltukNo = koltuk.koltukNo || "No?";
            const fiyat = rez.fiyat || 0;
            const pnr = b.biletNo || b.qrKod || "PNR-YOK";
  
            // HTML Kartını Oluştur
            list.innerHTML += `
              <div class="ticket-card animate__animated animate__fadeInUp">
                
                <div class="ticket-left">
                    <div class="company-name">
                        <i class="fa-solid fa-ticket"></i> ${firmaAdi}
                    </div>
                    <div class="pnr-badge">
                        PNR: ${pnr}
                    </div>
                </div>
        
                <div class="ticket-center">
                    <div class="route-row">
                        ${kalkis} <i class="fa-solid fa-arrow-right-long" style="margin:0 10px; color:#aaa;"></i> ${varis}
                    </div>
                    <div class="date-row">
                        <span><i class="fa-regular fa-calendar"></i> ${tarih}</span>
                        <span><i class="fa-regular fa-clock"></i> ${saat}</span>
                    </div>
                </div>
        
                <div class="ticket-right">
                    <span class="seat-info">Koltuk: ${koltukNo}</span>
                    <div class="price-display">${fiyat} ₺</div>
                    
                    <button onclick="biletIptal(${b.biletId})" class="btn-cancel">
                        <i class="fas fa-trash-alt"></i> İptal
                    </button>
                </div>
        
              </div>
            `;
        });
  
    } catch (err) {
        console.error("Bilet yükleme hatası:", err);
        list.innerHTML = `<p style="color:red; text-align:center;">Biletler yüklenirken hata oluştu: ${err.message}</p>`;
    }
  });
  
  // İPTAL FONKSİYONU (Aynen kalabilir)
  async function biletIptal(id) {
    if(!confirm("Biletinizi iptal etmek istediğinize emin misiniz?")) return;
  
    try {
        const res = await fetch(`/api/bilet/${id}`, { method: 'DELETE' });
        if(res.ok) {
            alert("Bilet iptal edildi.");
            location.reload();
        } else {
            alert("İptal edilemedi. Hata kodu: " + res.status);
        }
    } catch (err) {
        console.error(err);
        alert("Bir hata oluştu.");
    }
  }

  // --- FAVORİ EKLEME FONKSİYONU (Aynen kalabilir) ---
window.toggleFavorite = async (rotaId, aracId, firmaId) => {
    const user = JSON.parse(localStorage.getItem("user"));
    
    // Giriş yapmamışsa uyar
    if(!user) {
        alert("Favorilere eklemek için önce giriş yapmalısınız! 🔒");
        window.location.href = "/giris";
        return;
    }

    const btn = event.currentTarget; // Tıklanan butonu al
    const icon = btn.querySelector("i");
    
    // Görsel geri bildirim (Animasyon)
    icon.classList.remove("fa-regular");
    icon.classList.add("fa-solid");
    icon.style.color = "#e74c3c"; // Kırmızı yap
    btn.classList.add("animate__animated", "animate__heartBeat");

    try {
        const payload = {
            kullanici: { kullaniciId: user.kullaniciId },
            rotaPlan: { 
                id: { rotaId, aracId, firmaId } 
            },
            firma: { firmaId: firmaId } // Firma ID'sini de gönderiyoruz
        };

        const res = await fetch("/api/favoriler", {
            method: "POST",
            headers: {"Content-Type":"application/json"},
            body: JSON.stringify(payload)
        });

        if(res.ok) {
            // Başarılı!
        } else {
            alert("Bir hata oluştu veya zaten ekli.");
            // Hata olursa geri al
            icon.classList.add("fa-regular");
            icon.classList.remove("fa-solid");
            icon.style.color = ""; 
        }
    } catch(e) {
        console.error(e);
        alert("Sunucu hatası.");
    }
};