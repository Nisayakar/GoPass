document.addEventListener("DOMContentLoaded", async () => {
  const list = document.getElementById("reservation-list");
  const user = JSON.parse(localStorage.getItem("user"));

  if (!user) {
    location.href = "/giris";
    return;
  }

  try {
    const res = await fetch(`/api/rezervasyonlar/kullanici/${user.kullaniciId}`);
    const data = await res.json();

    if (data.length === 0) {
      list.innerHTML = "<p style='text-align:center;'>Aktif rezervasyonunuz bulunmuyor.</p>";
      return;
    }

    list.innerHTML = "";
    data.forEach(r => {
      // RotaPlan üzerinden detaylara erişim
      const plan = r.rotaPlan; 
      const kalkis = plan?.rota?.kalkisKonum?.sehir || "?";
      const varis = plan?.rota?.varisKonum?.sehir || "?";
      const tarih = plan?.seferTarihi || "";
      const saat = plan?.seferSaati || "";

      let butonlar = "";
      // Sadece 'Beklemede' olanlar için buton göster
      if (r.durum === 'Beklemede') {
          butonlar = `
            <div class="actions" style="margin-top:10px; display:flex; gap:10px;">
                <button onclick="biletAl(${r.rezervasyonId}, ${r.fiyat})" class="btn-success">Satın Al</button>
                <button onclick="rezervasyonIptal(${r.rezervasyonId})" class="btn-danger">İptal Et</button>
            </div>
          `;
      } else {
          butonlar = `<span class="badge badge-info">${r.durum}</span>`;
      }

      list.innerHTML += `
        <div class="sefer-card" style="flex-direction: column; align-items: flex-start;">
          <div style="width:100%; display:flex; justify-content:space-between; margin-bottom:10px;">
             <strong>${kalkis} <i class="fas fa-arrow-right"></i> ${varis}</strong>
             <span>${tarih} | ${saat}</span>
          </div>
          
          <div class="ticket-info" style="width:100%; justify-content: space-between;">
             <div>
                Koltuk No: <b>${r.koltuk?.koltukNo}</b><br>
                Firma: ${plan?.firma?.firmaAdi}
             </div>
             <div style="text-align:right">
                <div class="fiyat">${r.fiyat} ₺</div>
             </div>
          </div>
          
          ${butonlar}
        </div>
      `;
    });
  } catch (e) {
    console.error(e);
    list.innerHTML = "Veriler yüklenirken hata oluştu.";
  }
});

document.addEventListener("DOMContentLoaded", async () => {
  const list = document.getElementById("reservation-list");
  const user = JSON.parse(localStorage.getItem("user"));

  if (!user) {
    location.href = "/giris";
    return;
  }

  try {
    const res = await fetch(`/api/rezervasyonlar/kullanici/${user.kullaniciId}`);
    const data = await res.json();

    if (data.length === 0) {
      list.innerHTML = "<p>Henüz rezervasyon yok.</p>";
      return;
    }

    list.innerHTML = "";
    data.forEach(r => {
      // Eğer zaten biletlendiyse satın al butonunu gösterme
      // Not: Backend'den durum 'Biletlendi' geliyorsa kontrol edebiliriz.
      // Şimdilik varsayılan olarak buton ekliyoruz.
      
      list.innerHTML += `
        <div class="ticket-card" style="border:1px solid #ccc; padding:15px; margin:10px 0;">
          <div class="ticket-info">
            <b>Koltuk No:</b> ${r.koltuk?.koltukNo || '-'}<br>
            <b>Durum:</b> ${r.durum}<br>
            <b>Fiyat:</b> ${r.fiyat} ₺
          </div>
          <div class="actions" style="margin-top:10px;">
             <button onclick="biletAl(${r.rezervasyonId}, ${r.fiyat})" style="background:#28a745; color:white; padding:5px 10px; border:none; cursor:pointer;">Satın Al / Biletle</button>
             <button onclick="rezervasyonIptal(${r.rezervasyonId})" style="background:#dc3545; color:white; padding:5px 10px; border:none; cursor:pointer;">İptal Et</button>
          </div>
        </div>
      `;
    });
  } catch (e) {
    list.innerHTML = "Veriler yüklenemedi. Entity döngü hatası olabilir.";
  }
});

async function biletAl(rezervasyonId, tutar) {
  if(!confirm(tutar + "₺ ödeme yapıp bilet oluşturmak istiyor musunuz?")) return;

  const user = JSON.parse(localStorage.getItem("user"));

  // 1. Adım: Bileti Oluştur (Basitleştirilmiş akış)
  const res = await fetch("/api/bilet/satin-al", {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({
      kullanici: { kullaniciId: user.kullaniciId },
      rezervasyon: { rezervasyonId: rezervasyonId },
      tutar: tutar,
      biletNo: "PNR-" + Math.floor(Math.random() * 1000000) // Rastgele PNR
    })
  });

  if (res.ok) {
    alert("Bilet başarıyla oluşturuldu! Biletlerim sayfasına bakınız.");
    // Opsiyonel: Rezervasyon durumunu güncellemek için ayrı bir istek atılabilir
    location.reload();
  } else {
    alert("Satın alma sırasında hata oluştu.");
  }
}

async function rezervasyonIptal(id) {
    if(!confirm("Rezervasyonu iptal etmek istiyor musunuz?")) return;
    
    const res = await fetch(`/api/rezervasyonlar/${id}`, { method: "DELETE" });
    if(res.ok) {
        alert("Rezervasyon iptal edildi.");
        location.reload();
    } else {
        alert("Hata oluştu.");
    }
}