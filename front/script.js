function sendRequest(method, endpoint, body = null) {
    const url = `http://localhost:8080/supplier/supplier${endpoint}`;

    return fetch(url, {
        method: method,
        headers: {
            'Content-Type': 'application/json'
        },
        body: body ? JSON.stringify(body) : null
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json();
        })
        .catch(error => console.error('Error:', error));
}

function displayMessage(message, targetElementId, messageType = 'success') {
    const targetElement = document.getElementById(targetElementId);
    targetElement.innerHTML = `<div class="alert alert-${messageType}" role="alert">${message}</div>`;
    setTimeout(() => {
        targetElement.innerHTML = '';
    }, 5000);
}

function clearFields(fieldIds) {
    fieldIds.forEach(id => {
        document.getElementById(id).value = '';
    });
}

function isValidCnpj(cnpj) {
    // Expressão regular para validar CNPJ (formato: XX.XXX.XXX/XXXX-XX)
    const cnpjRegex = /^\d{2}\.\d{3}\.\d{3}\/\d{4}-\d{2}$/;
    return cnpjRegex.test(cnpj);
}

function isValidEmail(email) {
    // Expressão regular para validar e-mail
    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    return emailRegex.test(email);
}

function addSupplier() {
    const cnpj = document.getElementById('addCnpj').value;
    const name = document.getElementById('addName').value;
    const email = document.getElementById('addEmail').value;
    const description = document.getElementById('addDescription').value;

    if (!cnpj || !name || !email || !description) {
        displayMessage('All fields must be filled.', 'addSupplierMessage', 'danger');
        return;
    }

    if (!isValidCnpj(cnpj) || !isValidEmail(email)) {
        displayMessage('Invalid CNPJ or e-mail.', 'addSupplierMessage', 'danger');
        return;
    }

    const supplier = { cnpj, name, email, description };

    sendRequest('POST', '', supplier)
        .then(data => {
            console.log('Added supplier:', data);
            displayMessage(`Supplier added: ${data.name} (CNPJ: ${data.cnpj})`, 'addSupplierMessage');
            clearFields(['addCnpj', 'addName', 'addEmail', 'addDescription']);
        })
        .catch(error => {
            console.error('Error adding supplier:', error);
            displayMessage('Failed to add supplier.', 'addSupplierMessage', 'danger');
        });
}

function editSupplier() {
    const cnpj = document.getElementById('editCnpj').value;
    const name = document.getElementById('editName').value;
    const email = document.getElementById('editEmail').value;
    const description = document.getElementById('editDescription').value;

    if (!cnpj || !name || !email || !description) {
        displayMessage('All fields must be filled.', 'editSupplierMessage', 'danger');
        return;
    }

    if (!isValidCnpj(cnpj) || !isValidEmail(email)) {
        displayMessage('Invalid CNPJ or e-mail.', 'editSupplierMessage', 'danger');
        return;
    }

    const updatedSupplier = { name, email, description };

    sendRequest('PUT', `?cnpj=${cnpj}`, updatedSupplier)
        .then(data => {
            console.log('Updated supplier:', data);
            displayMessage(`Supplier updated: ${data.name} (CNPJ: ${data.cnpj})`, 'editSupplierMessage');
        })
        .catch(error => {
            console.error('Error updating supplier:', error);
            displayMessage('Failed to update supplier.', 'editSupplierMessage', 'danger');
        });
}

function getSupplier() {
    const cnpj = document.getElementById('getCnpj').value;

    if (!cnpj) {
        displayMessage('CNPJ field must be filled.', 'getSupplierMessage', 'danger');
        return;
    }

    if (!isValidCnpj(cnpj)) {
        displayMessage('Invalid CNPJ.', 'getSupplierMessage', 'danger');
        return;
    }

    sendRequest('GET', `?cnpj=${cnpj}`)
        .then(data => {
            console.log('Supplier:', data);
            displayMessage(`Supplier: Name - ${data.name}, Email - ${data.email}, Description - ${data.description}`, 'getSupplierMessage');
        })
        .catch(error => {
            console.error('Error getting supplier:', error);
            displayMessage('Supplier not found.', 'getSupplierMessage', 'danger');
        });
}

function deleteSupplier() {
    const cnpj = document.getElementById('deleteCnpj').value;

    if (!cnpj) {
        displayMessage('CNPJ field must be filled.', 'deleteSupplierMessage', 'danger');
        return;
    }

    if (!isValidCnpj(cnpj)) {
        displayMessage('Invalid CNPJ.', 'deleteSupplierMessage', 'danger');
        return;
    }

    sendRequest('DELETE', `?cnpj=${cnpj}`)
        .then(() => {
            console.log('Supplier deleted');
            displayMessage(`Supplier deleted`, 'deleteSupplierMessage');
            clearFields(['deleteCnpj']);
        })
        .catch(error => {
            console.error('Error deleting supplier:', error);
            displayMessage('Failed to delete supplier.', 'deleteSupplierMessage', 'danger');
        });
}

function getAllSuppliers() {
    sendRequest('GET', '')
        .then(data => {
            console.log('Suppliers:', data);
            let message = 'Suppliers:<ul>';
            data.forEach(supplier => {
                message += `<li>Name - ${supplier.name}, Email - ${supplier.email}, Description - ${supplier.description}</li>`;
            });
            message += '</ul>';
            displayMessage(message, 'getAllSuppliersMessage');
        })
        .catch(error => {
            console.error('Error getting all suppliers:', error);
            displayMessage('Failed to fetch all suppliers.', 'getAllSuppliersMessage', 'danger');
        });
}
