document.addEventListener("DOMContentLoaded", async () => {
  const list = document.getElementById("ticket-list");
  const user = JSON.parse(localStorage.getItem("user"));

  if (!user) {
    location.href = "/giris";
    return;
  }

  const res = await fetch(`/api/bilet/kullanici/${user.kullaniciId}`);
  const data = await res.json();

  if (data.length === 0) {
    list.innerHTML = "<p>Henüz biletiniz yok.</p>";
    return;
  }

  list.innerHTML = "";
  data.forEach(b => {
    list.innerHTML += `
      <div class="ticket-card" style="border:1px solid #ccc; padding:15px; margin:10px 0;">
        <div class="ticket-info">
          <b>PNR:</b> ${b.biletNo}<br>
          <b>Koltuk:</b> ${b.rezervasyon?.koltuk?.koltukNo || '?'}<br>
          <b>Sefer Durumu:</b> ${b.rezervasyon?.durum || 'Aktif'}<br>
          <b>Fiyat:</b> ${b.rezervasyon?.fiyat || 0} ₺
        </div>
        <button onclick="biletIptal(${b.biletId})" style="background:#d9534f; color:white; border:none; padding:5px 10px; margin-top:5px; cursor:pointer;">Bileti İptal Et</button>
      </div>
    `;
  });
});

async function biletIptal(id) {
    if(!confirm("Biletinizi iptal etmek istediğinize emin misiniz?")) return;

    const res = await fetch(`/api/bilet/${id}`, {
        method: 'DELETE'
    });

    if(res.ok) {
        alert("Bilet iptal edildi.");
        location.reload();
    } else {
        alert("İptal edilemedi.");
    }
}