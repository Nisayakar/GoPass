document.addEventListener("DOMContentLoaded", async () => {
    const list = document.getElementById("reservation-list");
    const user = JSON.parse(localStorage.getItem("user"));
  
    // 1. Giriş Kontrolü
    if (!user) {
      location.href = "/giris";
      return;
    }
  
    // 2. Rezervasyonları Çek
    try {
      const res = await fetch(`/api/rezervasyonlar/kullanici/${user.kullaniciId}`);
      if (!res.ok) throw new Error("Veri çekilemedi");
      
      const data = await res.json();
  
      // 3. Hiç Rezervasyon Yoksa
      if (!data || data.length === 0) {
        list.innerHTML = `
            <div style="text-align:center; color:white; padding:50px;">
                <i class="fa-regular fa-calendar-xmark" style="font-size:3rem; margin-bottom:20px; color:rgba(255,255,255,0.3);"></i>
                <h3>Henüz rezervasyonunuz yok.</h3>
                <p style="color:#aaa;">Beğendiğiniz seferleri rezerve edip sonra alabilirsiniz.</p>
                <a href="/" class="btn-buy" style="display:inline-block; width:auto; margin-top:20px; text-decoration:none;">Sefer Ara</a>
            </div>`;
        return;
      }
  
      list.innerHTML = ""; // Listeyi temizle
  
      // 4. Kartları Oluştur
      data.forEach(r => {
        // --- GÜVENLİ VERİ ALMA (Null Check) ---
        const plan = r.rotaPlan || {};
        const rota = plan.rota || {};
        const firma = r.firma || (plan.firma || {}); // Firma bazen dışta bazen içte olabilir
        const koltuk = r.koltuk || {};
        
        const firmaAdi = firma.firmaAdi || "Firma ?";
        const kalkis = (rota.kalkisKonum && rota.kalkisKonum.sehir) ? rota.kalkisKonum.sehir : "Kalkış?";
        const varis = (rota.varisKonum && rota.varisKonum.sehir) ? rota.varisKonum.sehir : "Varış?";
        
        const tarih = plan.seferTarihi || "-";
        const saat = plan.seferSaati || "-";
        const koltukNo = koltuk.koltukNo || "?";
        const fiyat = r.fiyat || 0;
        const durum = r.durum || "Belirsiz";
  
        // Duruma göre CSS sınıfı belirle
        let durumClass = "beklemede";
        if (durum === "Biletlendi") durumClass = "biletlendi";
        else if (durum === "İptal") durumClass = "iptal";
  
        // Sadece "Beklemede" ise Satın Al butonu göster
        let buyButtonHTML = "";
        if (durum === 'Beklemede') {
            buyButtonHTML = `
            <button onclick="biletAl(${r.rezervasyonId}, ${fiyat})" class="btn-buy">
                <i class="fa-solid fa-credit-card"></i> Satın Al
            </button>`;
        }
  
        // HTML KARTI
        list.innerHTML += `
          <div class="ticket-card animate__animated animate__fadeInUp">
            
            <div class="ticket-left">
                <div class="company-name">
                    <i class="fa-solid fa-calendar-check"></i> ${firmaAdi}
                </div>
                <span class="status-badge ${durumClass}">${durum}</span>
            </div>
    
            <div class="ticket-center">
                <div class="route-row">
                    ${kalkis} <i class="fa-solid fa-arrow-right-long" style="color:var(--primary); margin:0 15px;"></i> ${varis}
                </div>
                <div class="date-row">
                    <span><i class="fa-regular fa-calendar"></i> ${tarih}</span>
                    <span><i class="fa-regular fa-clock"></i> ${saat}</span>
                </div>
            </div>
    
            <div class="ticket-right">
                <span class="seat-info" style="margin-bottom:5px;">Koltuk: ${koltukNo}</span>
                <div class="price-display" style="font-size:1.8rem; margin-bottom:10px;">${fiyat} ₺</div>
                
                <div class="action-buttons">
                    ${buyButtonHTML}
                    <button onclick="rezervasyonIptal(${r.rezervasyonId})" class="btn-cancel">
                        <i class="fas fa-times"></i> İptal Et
                    </button>
                </div>
            </div>
    
          </div>
        `;
      });
  
    } catch (e) {
      console.error(e);
      list.innerHTML = `<p style="color:white; text-align:center;">Rezervasyonlar yüklenirken hata oluştu.</p>`;
    }
  });
  
  // --- İŞLEM FONKSİYONLARI ---
  
  async function biletAl(rezervasyonId, tutar) {
    if(!confirm(`${tutar} TL ödeme yapıp bileti satın almak istiyor musunuz?`)) return;
  
    const user = JSON.parse(localStorage.getItem("user"));
  
    try {
        const res = await fetch("/api/bilet/satin-al", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({
            kullanici: { kullaniciId: user.kullaniciId },
            rezervasyon: { rezervasyonId: rezervasyonId },
            tutar: tutar,
            biletNo: "PNR-" + Math.floor(100000 + Math.random() * 900000) 
            })
        });
    
        if (res.ok) {
            alert("Ödeme başarılı! Biletiniz oluşturuldu.");
            location.href = "/ticket"; // Biletlerim sayfasına yönlendir
        } else {
            const err = await res.json().catch(() => ({}));
            alert("İşlem başarısız: " + (err.message || "Bilinmeyen hata"));
        }
    } catch (error) {
        console.error(error);
        alert("Bağlantı hatası.");
    }
  }
  
  async function rezervasyonIptal(id) {
      if(!confirm("Rezervasyonu iptal etmek istediğinize emin misiniz?")) return;
      
      try {
        const res = await fetch(`/api/rezervasyonlar/${id}`, { method: "DELETE" });
        if(res.ok) {
            alert("Rezervasyon iptal edildi.");
            location.reload();
        } else {
            alert("İptal işlemi başarısız oldu.");
        }
      } catch (error) {
          console.error(error);
          alert("Bir hata oluştu.");
      }
  }