// ======================================================
// FAVORITES.JS - FİNAL (Dark Glass Ticket Design)
// ======================================================

document.addEventListener("DOMContentLoaded", async () => {

    const list = document.getElementById("fav-list");
    if (!list) return;

    // 🔐 GİRİŞ KONTROLÜ
    const userStr = localStorage.getItem("user");
    if (!userStr) {
        window.location.href = "/giris";
        return;
    }
    const user = JSON.parse(userStr);

    try {
        // 1️⃣ FAVORİLERİ ÇEK
        const res = await fetch(`/api/favoriler/kullanici/${user.kullaniciId}`);
        if (!res.ok) throw new Error("Sunucu hatası");
        const data = await res.json();

        // 2️⃣ BOŞSA UYARI
        if (!Array.isArray(data) || data.length === 0) {
            list.innerHTML = `
                <div style="text-align:center; padding: 50px; background: rgba(255,255,255,0.05); border-radius: 20px; border: 1px solid rgba(255,255,255,0.1);">
                    <i class="fa-solid fa-heart-crack" style="font-size: 3rem; color: rgba(255,255,255,0.3); margin-bottom: 20px;"></i>
                    <h3 style="color: white;">Henüz favori rotanız yok.</h3>
                </div>`;
            return;
        }

        // 3️⃣ LİSTELE
        list.innerHTML = "";
        data.forEach(f => {
            const firmaAdi = f.firma ? f.firma.firmaAdi : "Firma Bilgisi Yok";
			const kalkis = f.rota?.kalkisKonum?.sehir?.sehirAdi || "?";
			const varis  = f.rota?.varisKonum?.sehir?.sehirAdi  || "?";

            
            // Link oluştur
            const searchParams = `?from=${kalkis}&to=${varis}`;

            list.innerHTML += `
                <div class="favori-card">
                    <div class="fav-left">
                        <div class="company-name">
                            <i class="fa-solid fa-bus"></i> ${firmaAdi}
                        </div>
                        <span class="fav-badge">FAVORİ ROTA</span>
                    </div>

                    <div class="fav-center">
                        <div class="route-row">
                            ${kalkis} <i class="fa-solid fa-arrow-right-long"></i> ${varis}
                        </div>
                       
                    </div>

                    <div class="fav-right">
                        <a href="/" class="btn-book">
                            Bilet Bul
                        </a>
                        
                        <button onclick="favoriSil(${f.favoriId})" class="btn-remove">
                            <i class="fa-solid fa-trash-can"></i> Sil
                        </button>
                    </div>
                </div>
            `;
        });

    } catch (e) {
        console.error("Favori yükleme hatası:", e);
    }
});

async function favoriSil(id) {
    if (!confirm("Bu favoriyi silmek istediğinize emin misiniz?")) return;
    try {
        await fetch(`/api/favoriler/${id}`, { method: "DELETE" });
        location.reload();
    } catch (e) {
        alert("Silinemedi");
    }
}

// EKLEME FONKSİYONU (Aynı kalıyor)
async function toggleFavorite(rotaId, firmaId) {
    const userStr = localStorage.getItem("user");
    if (!userStr) {
        alert("Giriş yapmalısınız.");
        window.location.href = "/giris";
        return;
    }
    const user = JSON.parse(userStr);
    try {
        const payload = {
            kullanici: { kullaniciId: user.kullaniciId },
            rota: { rotaId: rotaId },
            firma: { firmaId: firmaId }
        };
        const res = await fetch("/api/favoriler", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(payload)
        });
        if (res.ok) alert("Favorilere eklendi! ⭐");
        else if (res.status === 409) alert("Zaten ekli.");
        else alert("Hata oluştu.");
    } catch (e) { console.error(e); }
}