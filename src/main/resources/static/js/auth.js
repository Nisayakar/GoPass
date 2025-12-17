document.addEventListener("DOMContentLoaded", () => {

  // LOGIN
  document.getElementById("login-form")?.addEventListener("submit", async e => {
    e.preventDefault();

    const res = await fetch("/api/auth/login", {
      method: "POST",
      headers: {"Content-Type":"application/json"},
      body: JSON.stringify({
        email: email.value,
        password: password.value
      })
    });

    if (!res.ok) {
      alert("Giriş başarısız");
      return;
    }

    const user = await res.json();
    localStorage.setItem("user", JSON.stringify(user));
    location.href = "/";
  });

  // REGISTER
  document.getElementById("register-form")?.addEventListener("submit", async e => {
    e.preventDefault();

    if (password.value !== password2.value) {
      alert("Şifreler eşleşmiyor");
      return;
    }

    const res = await fetch("/api/auth/register", {
      method: "POST",
      headers: {"Content-Type":"application/json"},
      body: JSON.stringify({
        fullName: full_name.value,
        email: email.value,
        password: password.value
      })
    });

    if (!res.ok) {
      alert("Kayıt başarısız");
      return;
    }

    const user = await res.json();
    localStorage.setItem("user", JSON.stringify(user));
    location.href = "/";
  });
});
