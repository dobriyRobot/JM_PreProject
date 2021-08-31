const url = 'http://localhost:8080/api/v1/admin';

function deleteUser(method) {
    let form = document.getElementById("deleteUser");
    event.preventDefault();
    fetch((url + '/' + form.deleteId.value), {
        method: method,
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            id: form.deleteId.value,
            name: form.deleteFirstname.value,
            surname: form.deleteLastname.value,
            age: form.deleteAge.value,
            email: form.deleteEmail.value,
            password: form.deletePassword.value,
            roles: getRoles(form.deleteRole)
        })
    })
        .then(response => response.status)
    document.getElementById(form.deleteId.value).remove();
    document.getElementById("deleteModal").click();
}

function deleteUserForm(userId) {
    let optionDel = '';
    let form = document.getElementById('deleteUser');
    fetch(url + '/' + userId)
        .then(response => response.json())
        .then(el => {
            form.deleteId.value = el.id;
            form.deleteFirstname.value = el.name;
            form.deleteLastname.value = el.surname;
            form.deleteAge.value = el.age;
            form.deleteEmail.value = el.email;
            form.deletePassword.value = el.password;

            let arr = el.roles.map(value => {
                return value.id === 1 ? 'ADMIN' : 'USER';
            });
            for (let key in arr) {
                let role = arr[key];
                optionDel += "<option value='" + role + "'>" + role + "</option>";
            }
            document.getElementById("deleteRole").innerHTML = optionDel;
        });
}

function editUser(method) {
    let editRow = '';
    let form = document.getElementById("editUser");
    event.preventDefault();
    fetch((url), {
        method: method,
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            id: form.editId.value,
            name: form.editFirstname.value,
            surname: form.editLastname.value,
            age: form.editAge.value,
            email: form.editEmail.value,
            password: form.editPassword.value,
            roles: getRoles(form.editRole)
        })
    })
        .then(response => response.json())
        .then(data => {
            editRow = `
                <tr id="${data.id}">
                   <td>${data.id}</td>
                   <td>${data.name}</td>
                   <td>${data.surname}</td>
                   <td>${data.email}</td>
                   <td>${data.age}</td>
                   <td>${data.modifyRoles}</td>
                   <td><a type="button" class="btn btn-info" data-bs-toggle="modal" data-bs-target="#editModal"
                            onclick="editUserForm(${data.id})" id="edit-user">Edit</a></td>
                   <td><a type="button" class="btn btn-danger" data-bs-toggle="modal" data-bs-target="#deleteModal"
                            onclick="deleteUserForm(${data.id})" id="delete-user">Delete</a></td>
                </tr>
            `;
            document.getElementById(data.id).innerHTML = editRow;
        })
    document.getElementById("editModal").click();
}

function editUserForm(userId) {
    let select = document.getElementById("editRole");
    let form = document.getElementById("editUser");
    form.reset();
    fetch((url + '/' + userId),
        )
        .then(response => response.json())
        .then(el => {
            form.editId.value = el.id;
            form.editFirstname.value = el.name;
            form.editLastname.value = el.surname;
            form.editAge.value = el.age;
            form.editEmail.value = el.email;
            form.editPassword.value = el.password;

            let arr = el.roles.map(value => {
                return value.id === 1 ? 'ADMIN' : 'USER';
            });
            for (let key in arr) {
                select.options[key].selected = true;
            }
        });
}

function getRoles(formRole) {
    return Array.from(formRole)
        .filter(option => option.selected)
        .map(option => option.value)
        .map(value => {
            return value === 'ROLE_ADMIN' ? {id: 1, role: 'ROLE_ADMIN'} : {id: 2, role: 'ROLE_USER'};
        })
}

const allUsers = (user) => {
    let output = '';
    user.forEach(el => {
        output += `
                <tr id="${el.id}">
                   <td>${el.id}</td>
                   <td>${el.name}</td>
                   <td>${el.surname}</td>
                   <td>${el.email}</td>
                   <td>${el.age}</td>
                   <td>${el.modifyRoles}</td>             
                   <td><a type="button" class="btn btn-info" data-bs-toggle="modal" data-bs-target="#editModal"
                            onclick="editUserForm(${el.id})" id="edit-user">Edit</a></td>
                   <td><a type="button" class="btn btn-danger" data-bs-toggle="modal" data-bs-target="#deleteModal" 
                            onclick="deleteUserForm(${el.id})" id="delete-user">Delete</a></td>
                </tr>
                `;
    });
    document.getElementById("data").innerHTML += output;
}

if (getCookie('Admin') === 'true') {
    fetch(url)
        .then(response => response.json())
        .then(data => allUsers(data))
}

fetch(url + '/' + getCookie('UserId'))
    .then(response => response.json())
    .then(data => {
        document.getElementById("nameCurrentUser").innerHTML = `<span>${data.email}</span>`;
        document.getElementById("roleCurrentUser").innerHTML = `<span>${data.modifyRoles}</span>`;
        let current = '';
            current = `
                <tr>
                   <td>${data.id}</td>
                   <td>${data.name}</td>
                   <td>${data.surname}</td>
                   <td>${data.age}</td>
                   <td>${data.email}</td>
                   <td>${data.modifyRoles}</td>             
                `;
        document.getElementById("currentUser").innerHTML = current;
    })

function addNewUser(method) {
    let form = document.getElementById("formAdd");
    event.preventDefault();
    fetch(url, {
        method: method,
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            name: form.firstname.value,
            surname: form.lastname.value,
            age: form.age.value,
            email: form.email.value,
            password: form.password.value,
            roles: getRoles(form.role)
        })
    })
        .then(response => response.json())
        .then(data => {
            const array = [];
            array.push(data);
            allUsers(array)
        })
    document.getElementById("Users-table-tab").click();
}

function getCookie(name) {
    let matches = document.cookie.match(new RegExp(
        "(?:^|; )" + name.replace(/([\.$?*|{}\(\)\[\]\\\/\+^])/g, '\\$1') + "=([^;]*)"
    ));
    return matches ? decodeURIComponent(matches[1]) : undefined;
}
