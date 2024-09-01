document.getElementById('registration-form').addEventListener('submit', function(e) {
    e.preventDefault();

    const userDto = {
        username: document.getElementById('register-username').value,
        password: document.getElementById('register-password').value
    };

    fetch('/api/registration_controller/register', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(userDto)
    })
        .then(response => {
            if (response.ok) {
                alert('Регистрация прошла успешно!');
                window.location.href = '/login';
            } else {
                alert('Ошибка при регистрации.');
            }
        })
        .catch(error => console.error('Ошибка:', error));
});