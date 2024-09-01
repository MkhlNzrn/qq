document.addEventListener('DOMContentLoaded', function () {
    const fetchButton = document.getElementById('getSalesButton');
    const preloader = document.getElementById('preloader');
    const content = document.getElementById('content');
    fetchButton.addEventListener('click', fetchData);

    function restoreContent() {
        let stored = localStorage.getItem("data");
        if (stored) {
            content.innerHTML = stored;
        }
        // localStorage.clear()
    }
    restoreContent();

    function fetchData() {
        preloader.style.display = 'block';
        let commentId;
        let isFirstRequest = true;
        if (isFirstRequest) {
            commentId = Math.floor(Math.random() * 401) + 100;
            isFirstRequest = false;
        } else {
            commentId = Math.floor(Math.random() * 201);
            isFirstRequest = true;
        }

        const url = `https://jsonplaceholder.typicode.com/comments?id=${commentId}`;

        fetch(url).then(response => response.json()).then(data => {
                preloader.style.display = 'none';
                Data(data);
            })
            .catch(error => {
                preloader.style.display = 'none';
                Error();
                console.error('Error:', error);
            });
    }

    function Data(data) {
        const testSales = data.sort(() => 0.5 - Math.random()).slice(0, 5);
        testSales.forEach(comment => {
            const div = document.createElement('div');
            div.className = 'content';
            div.innerHTML = `
                    <h2 style="text-align: center">${comment.name}</h2>
                    <p>${comment.body}</p>
                `;
            div.style.width = "300px"
            content.appendChild(div);

        })
        localStorage.setItem("data", content.innerHTML);
    }



    function Error() {
        content.innerHTML =
            '<div>Ошибка в получении акций</div>';
    }
});