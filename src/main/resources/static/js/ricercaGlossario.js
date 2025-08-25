// ricerca parola nel glossario

document.addEventListener('DOMContentLoaded', function () {
    const searchInput = document.getElementById('search');
    const tableRows = document.querySelectorAll('.glossario tbody tr');

    searchInput.addEventListener('input', function () {
        const query = this.value.toLowerCase();

        tableRows.forEach(row => {
            const cells = row.querySelectorAll('td');
            const match = Array.from(cells).some(cell =>
                cell.textContent.toLowerCase().includes(query)
            );
            row.style.display = match ? '' : 'none';
        });
    });
});