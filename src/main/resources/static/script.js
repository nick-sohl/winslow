document.addEventListener('DOMContentLoaded', () => {
  const form = document.querySelector('form');
  const statusEl = document.querySelector('#form-status');

  if (!form) {
    console.error('Student form not found. Please add id="student-form" to your form.');
    return;
  }

  form.addEventListener('submit', async (e) => {
    e.preventDefault();

    const submitBtn = form.querySelector('[type="submit"]');
    if (submitBtn) submitBtn.disabled = true;

    try {
      const formData = new FormData(form);

      const firstname = (formData.get('firstname') || '').toString().trim();
      const lastname = (formData.get('lastname') || '').toString().trim();
      const email = (formData.get('email') || '').toString().trim();
      const password = (formData.get('password') || '').toString();
      const deptRaw = formData.get('department');
      const departmentId =
        deptRaw === null || deptRaw === '' ? null : Number.parseInt(deptRaw.toString(), 10);

        console.log(firstname, lastname, email, password, deptRaw, departmentId)

      if (!firstname || !lastname || !email || !password) {
        throw new Error('Please fill out firstname, lastname, email, and password.');
      }
      if (departmentId !== null && Number.isNaN(departmentId)) {
        throw new Error('departmentId must be a number.');
      }

      const payload = {
        firstname,
        lastname,
        email,
        password,
        ...(departmentId !== null ? { departmentId } : {})
      };

      const res = await fetch('/api/students', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(payload)
      });

      if (!res.ok) {
        const text = await res.text().catch(() => '');
        throw new Error(`Failed to add student: ${res.status} ${text}`);
      }

      const saved = await res.json();
      showStatus(`Student created: ${saved.firstname} ${saved.lastname} (id: ${saved.id})`, 'success');
      form.reset();
    } catch (err) {
      console.error(err);
      showStatus(err.message || 'An unexpected error occurred.', 'error');
    } finally {
      if (submitBtn) submitBtn.disabled = false;
    }
  });

  function showStatus(message, type = 'info') {
    if (statusEl) {
      statusEl.textContent = message;
      statusEl.className = '';
      statusEl.classList.add('status', `status-${type}`);
    } else {
      // Fallback if no #form-status element exists
      alert(message);
    }
  }
});
