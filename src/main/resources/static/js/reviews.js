document.addEventListener("DOMContentLoaded", function () {
        let form = document.getElementById("form");
        const dataTable = document.getElementById('table');

        function saveTable() {
            localStorage.setItem("tableData", dataTable.innerHTML);
        }

        function restoreTable() {
            let storedTable = localStorage.getItem("tableData");
            if (storedTable) {
                dataTable.innerHTML = storedTable;
            }
            // localStorage.clear()
        }

        restoreTable();

        form.onsubmit = function (event) {
            event.preventDefault();

            const name = document.getElementById('name').value;
            const rating = document.getElementById('rating').value;
            const comment = document.getElementById('comment').value;

            let row = dataTable.insertRow();

            addCell(row, name);
            addCell(row, rating);
            addCell(row, comment);

            function addCell(row, text) {
                const cell = row.insertCell();
                cell.textContent = text;
            }


            form.reset();
            saveTable();
        };
    }
);
