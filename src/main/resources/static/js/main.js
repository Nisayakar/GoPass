document.addEventListener("DOMContentLoaded", () => {

  const authBox = document.getElementById("auth-box");
  const user = JSON.parse(localStorage.getItem("user") || "null");

  // 🔒 GİRİŞ KORUMASI
  const path = window.location.pathname;
  const protectedPages = ["/ticket", "/favorites"];
  if (protectedPages.includes(path) && !user) {
    alert("Bu sayfayı görmek için giriş yapmalısın.");
    window.location.href = "/giris";
    return;
  }

  if (!authBox) return;

  // ❌ Giriş yok
  if (!user) {
    authBox.innerHTML = `
      <a href="/giris" class="btn-outline">Giriş Yap</a>
      <a href="/kayit" class="btn-fill">Üye Ol</a>
    `;
    return;
  }

  // ✅ Giriş var
  const shownName =
    user.isim ||
    user.ad ||
    user.email ||
    "Kullanıcı";

  authBox.innerHTML = `
    <div style="display:flex; align-items:center; gap:12px;">
      <span style="color:white; font-weight:600;">
        <i class="fa-regular fa-user"></i> ${shownName}
      </span>
      <button id="logoutBtn" class="btn-outline"
        style="border-color:#ff4757; color:#ff4757;">
        Çıkış
      </button>
    </div>
  `;

  document.getElementById("logoutBtn").onclick = () => {
    localStorage.removeItem("user");
    location.href = "/";
  };
});
