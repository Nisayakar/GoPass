document.addEventListener("DOMContentLoaded", async () => {
  const list = document.getElementById("fav-list") || document.getElementById("favorites-list");
  if (!list) return;

  const user = JSON.parse(localStorage.getItem("user"));
  if (!user) {
    list.innerHTML = "<p>Giriş yapmalısınız.</p>";
    return;
  }

  try {
    const r = await fetch(`/api/favori/kullanici/${user.kullaniciId}`);
    if(!r.ok) throw new Error("Hata");
    
    const data = await r.json();

    if (!data || data.length === 0) {
      list.innerHTML = "<p>Henüz favori eklemediniz.</p>";
      return;
    }

    list.innerHTML = "";
    data.forEach(f => {
      // --- DEĞİŞİKLİK BURADA ---
      // Artık 'f.sefer' yok, 'f.rotaPlan' var.
      const rota = f.rotaPlan?.rota;
      const firma = f.rotaPlan?.firma;
      
      const kalkis = rota?.kalkisKonum?.sehir || "Bilinmiyor";
      const varis = rota?.varisKonum?.sehir || "Bilinmiyor";
      const firmaAdi = firma?.firmaAdi || "Firma Yok";

      list.innerHTML += `
        <div class="fav-card" style="border:1px solid #ddd; padding:10px; margin-bottom:10px; border-radius:5px;">
          <div class="ticket-info">
            <div class="company-name"><b>${firmaAdi}</b></div>
            <div class="route-info">${kalkis} → ${varis}</div>
            <button onclick="favoriSil(${f.favoriId})" style="float:right; background:red; color:white; border:none; padding:5px; cursor:pointer;">Sil</button>
          </div>
        </div>
      `;
    });

  } catch (err) {
    console.log(err);
    list.innerHTML = "<p>Veriler yüklenirken hata oluştu.</p>";
  }
});

// Favori Ekleme Fonksiyonu (Anasayfadaki listeleme kısmında kullanılacak)
// RotaPlanId composite olduğu için 3 id'yi de göndermeliyiz.
async function toggleFavorite(rotaId, aracId, firmaId){
  const user = JSON.parse(localStorage.getItem("user"));
  if(!user){
    alert("Favori için giriş yapmalısın");
    return;
  }

  await fetch("/api/favori",{
    method:"POST",
    headers:{ "Content-Type":"application/json" },
    body:JSON.stringify({
      kullanici: { kullaniciId: user.kullaniciId },
      // RotaPlan composite key olduğu için nesne yapısı şöyle olmalı:
      rotaPlan: {
        id: {
            rotaId: rotaId,
            aracId: aracId,
            firmaId: firmaId
        }
      }
    })
  });

  alert("Favorilere eklendi ⭐");
}

async function favoriSil(id) {
    if(!confirm("Silmek istiyor musunuz?")) return;
    await fetch(`/api/favori/${id}`, { method: "DELETE" });
    location.reload();
}