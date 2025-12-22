// Backend adresini buraya sabitliyoruz, böylece her yerde tekrar yazmak zorunda kalmayız.
// Eğer backend portun 8080 değilse burayı değiştir (örn: 9090)
const API_BASE_URL = "http://localhost:8080";

document.addEventListener("DOMContentLoaded", async () => {
    const list = document.getElementById("fav-list");
    
    // LocalStorage kontrolü
    const userStr = localStorage.getItem("user");
    if (!userStr) {
        location.href = "/giris";
        return;
    }
    const user = JSON.parse(userStr);

    try {
        // DÜZELTME 1: Tam URL kullanıldı (API_BASE_URL eklendi)
        const res = await fetch(`${API_BASE_URL}/api/favoriler/kullanici/${user.kullaniciId}`);

        // DÜZELTME 2: Sunucu hatası kontrolü (404 veya 500 gelirse kod patlamasın)
        if (!res.ok) {
            throw new Error(`Sunucu hatası: ${res.status}`);
        }

        const data = await res.json();

        // DÜZELTME 3: Gelen verinin dizi (Array) olup olmadığı kontrolü
        if (!Array.isArray(data) || data.length === 0) {
            list.innerHTML = `
                <div style="text-align:center; color:white; padding:50px;">
                    <i class="fa-regular fa-heart" style="font-size:3rem; margin-bottom:20px; color:rgba(255,255,255,0.3);"></i>
                    <h3>Favori listeniz boş.</h3>
                    <p style="color:#aaa;">Beğendiğiniz seferleri ekleyerek takip edebilirsiniz.</p>
                    <a href="/" style="display:inline-block; margin-top:20px; padding:10px 20px; background:var(--primary); color:white; border-radius:10px; text-decoration:none;">Sefer Ara</a>
                </div>`;
            return;
        }

        // Listeyi temizle ve doldur
        list.innerHTML = "";
        
        data.forEach(f => {
            // Güvenli veri çekimi (Null check)
            const plan = f.rotaPlan || {};
            const rota = plan.rota || {};
            const firma = f.firma || (plan.firma || {}); 

            const kalkis = (rota.kalkisKonum && rota.kalkisKonum.sehir) ? rota.kalkisKonum.sehir : "Kalkış?";
            const varis = (rota.varisKonum && rota.varisKonum.sehir) ? rota.varisKonum.sehir : "Varış?";
            const firmaAdi = firma.firmaAdi || "Firma ?";
            const tarih = plan.seferTarihi || "-";
            const saat = plan.seferSaati || "-";
            const fiyat = plan.biletFiyati || 0;

            // HTML Yapısı (Aynen korundu)
            list.innerHTML += `
              <div class="fav-card animate__animated animate__fadeInUp">
                
                <div class="fav-left">
                    <div class="company-name">
                        <i class="fa-solid fa-star"></i> ${firmaAdi}
                    </div>
                    <span class="fav-badge">Favori Sefer</span>
                </div>

                <div class="fav-center">
                    <div class="route-row">
                        ${kalkis} <i class="fa-solid fa-arrow-right-long"></i> ${varis}
                    </div>
                    <div class="date-row">
                        <span><i class="fa-regular fa-calendar"></i> ${tarih}</span>
                        <span><i class="fa-regular fa-clock"></i> ${saat}</span>
                    </div>
                </div>

                <div class="fav-right">
                    <div class="price-tag">${fiyat} ₺</div>
                    <button onclick="favoriSil(${f.favoriId})" class="btn-remove">
                        <i class="fa-solid fa-trash"></i> Kaldır
                    </button>
                </div>

              </div>
            `;
        });

    } catch (e) {
        console.error("Favoriler yüklenirken hata:", e);
        list.innerHTML = `<p style='color:white; text-align:center'>Veriler yüklenirken hata oluştu: ${e.message}</p>`;
    }
});

// Favori Silme Fonksiyonu
async function favoriSil(id) {
    if (!confirm("Favorilerden kaldırmak istediğinize emin misiniz?")) return;

    try {
        // DÜZELTME 4: Silme işleminde tam URL kullanımı
        const response = await fetch(`${API_BASE_URL}/api/favoriler/${id}`, { method: "DELETE" });
        
        if (response.ok) {
            location.reload();
        } else {
            alert("Silme işlemi başarısız oldu.");
        }
    } catch (e) {
        console.error(e);
        alert("Sunucuyla iletişim hatası.");
    }
}

// Favori Ekleme Fonksiyonu
async function toggleFavorite(rotaId, aracId, firmaId) {
    const userStr = localStorage.getItem("user");
    if (!userStr) {
        alert("Favori için giriş yapmalısın");
        return;
    }
    const user = JSON.parse(userStr);

    try {
        // DÜZELTME 5: Ekleme işleminde tam URL kullanımı
        // Not: Senin kodunda 'api/favori' olarak belirtilmişti, onu korudum.
        const response = await fetch(`${API_BASE_URL}/api/favoriler`, {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({
                kullanici: { kullaniciId: user.kullaniciId },
                rotaPlan: {
                    id: {
                        rotaId: rotaId,
                        aracId: aracId,
                        firmaId: firmaId
                    }
                }
            })
        });

        // Durum kontrolü
        if (response.ok) {
            alert("Favorilere eklendi ⭐");
        } 
        else if (response.status === 409) {
            const mesaj = await response.text();
            alert(mesaj); // "Zaten ekli" mesajı
        } 
        else {
            alert("Favoriye eklenirken bir hata oluştu.");
        }

    } catch (err) {
        console.error(err);
        alert("Sunucuyla iletişim hatası.");
    }
}