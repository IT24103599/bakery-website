document.addEventListener('DOMContentLoaded', function() {
    // Search functionality
    const searchInput = document.getElementById('searchInput');
    const inventoryTable = document.querySelector('.inventory-table tbody');

    if (searchInput && inventoryTable) {
        searchInput.addEventListener('input', function() {
            const searchTerm = this.value.toLowerCase();
            const rows = inventoryTable.querySelectorAll('tr');

            rows.forEach(row => {
                const text = row.textContent.toLowerCase();
                row.style.display = text.includes(searchTerm) ? '' : 'none';
            });
        });
    }

    // Category filter
    const categoryFilter = document.getElementById('categoryFilter');
    if (categoryFilter && inventoryTable) {
        categoryFilter.addEventListener('change', function() {
            const selectedCategory = this.value;
            const rows = inventoryTable.querySelectorAll('tr');

            rows.forEach(row => {
                if (selectedCategory === '') {
                    row.style.display = '';
                } else {
                    const categoryCell = row.querySelector('td:nth-child(3)');
                    if (categoryCell) {
                        const category = categoryCell.textContent.trim();
                        row.style.display = category === selectedCategory ? '' : 'none';
                    }
                }
            });
        });
    }

    // Low stock alert notification
    const lowStockItems = document.querySelectorAll('.low-stock');
    if (lowStockItems.length > 0) {
        setTimeout(() => {
            alert(`Warning: ${lowStockItems.length} item(s) are below their threshold levels!`);
        }, 1000);
    }
});