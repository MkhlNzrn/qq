page = window.document.location.href;
console.log(page);

if (window.document.location.href.includes("page_1.html")) {
    elem = document.getElementById("page_1");
    elem.style.background = "red";
    console.log(elem);
}

if (window.document.location.href.includes("page_2.html")) {
    elem = document.getElementById("page_2");
    elem.style.background = "red";
    console.log(elem);
}

if (window.document.location.href.includes("page_3.html")) {
    elem = document.getElementById("page_3");
    elem.style.background = "red";
    console.log(elem);
}

if (window.document.location.href.includes("page_4.html")) {
    elem = document.getElementById("page_4");
    elem.style.background = "red";
    console.log(elem);
}

if (window.document.location.href.includes("page_5.html")) {
    elem = document.getElementById("page_5");
    elem.style.background = "red";
    console.log(elem);
}

(function() {
    let startTime = new Date().getTime();

    window.addEventListener('load', function() {
        let loadTime = new Date().getTime() - startTime;

        let xhr = new XMLHttpRequest();
        xhr.open('GET', window.location.href, false);
        xhr.send(null);

        let serverProcessingTime = 0;
        if (xhr.status === 200) {
            serverProcessingTime = parseInt(xhr.getResponseHeader('X-Processing-Time')) || 0;
        }

        let totalTime = loadTime + serverProcessingTime;

        var loadState = document.createElement('div');
        loadState.style.position = 'fixed';
        loadState.style.backgroundColor = 'white';
        loadState.style.right = '0';
        loadState.style.top = '1px';
        loadState.style.textDecoration = "underline red";
        loadState.style.padding = "5px";
        loadState.innerHTML = 'Load time: ' + totalTime + ' мс (сервер: ' + serverProcessingTime + ' мс, DOM: ' + loadTime + ' мс)';

        document.body.appendChild(loadState);
    });
})();
