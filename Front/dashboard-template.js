const base = "https://pqrs-docs.s3.amazonaws.com/documents/"

const token = localStorage.getItem("jwtToken");



let navbarTemplate = `
    <nav class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">

        <!-- Sidebar Toggle (Topbar) -->
        <button id="sidebarToggleTop" class="btn btn-link d-md-none rounded-circle mr-3">
            <i class="fa fa-bars"></i>
        </button>

        <ul class="navbar-nav ml-auto">

            <li class="nav-item dropdown no-arrow d-sm-none">
                <a class="nav-link dropdown-toggle" href="#" id="searchDropdown" role="button"
                    data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    <i class="fas fa-search fa-fw"></i>
                </a>

                <div class="dropdown-menu dropdown-menu-right p-3 shadow animated--grow-in"
                    aria-labelledby="searchDropdown">
                    <form class="form-inline mr-auto w-100 navbar-search">
                        <div class="input-group">
                            <input type="text" class="form-control bg-light border-0 small"
                                placeholder="Search for..." aria-label="Search"
                                aria-describedby="basic-addon2">
                            <div class="input-group-append">
                                <button class="btn btn-primary" type="button">
                                    <i class="fas fa-search fa-sm"></i>
                                </button>
                            </div>
                        </div>
                    </form>
                </div>
            </li>

            <li class="nav-item dropdown no-arrow mx-1">
                <a class="nav-link dropdown-toggle" href="#" id="alertsDropdown" role="button"
                    data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    <i class="fas fa-bell fa-fw"></i>

                    <span class="badge badge-danger badge-counter">1</span>
                </a>

                <div class="dropdown-list dropdown-menu dropdown-menu-right shadow animated--grow-in"
                    aria-labelledby="alertsDropdown">
                    <h6 class="dropdown-header">
                        Notificaciones
                    </h6>
                    <div id="pqrs">
                    </div>

                </div>
            </li>


            <div class="topbar-divider d-none d-sm-block"></div>

            <!-- Info de usuario -->
            <li class="nav-item dropdown no-arrow">
                <a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button"
                    data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    <span class="mr-2 d-none d-lg-inline text-gray-600 small" id="nombre">Douglas McGee</span>
                    <img class="img-profile rounded-circle" id="perfil" src="">
                </a>

                <div class="dropdown-menu dropdown-menu-right shadow animated--grow-in"
                    aria-labelledby="userDropdown">
                    <a class="dropdown-item" href="editar-perfil-admin.html">
                        <i class="fas fa-user fa-sm fa-fw mr-2 mt-1 text-gray-400" ></i>
                        Mi Perfil
                        
                    </a>

                    <div class="dropdown-divider"></div>
                    <a class="dropdown-item" href="#" data-toggle="modal" data-target="#logoutModal">
                        <i class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>
                        Cerrar sesión
                    </a>
                </div>
            </li>

        </ul>
    </nav>
    `;


let sidebarTemplate = `
<!-- Sidebar -->


    <!-- Sidebar - Brand -->
    <a class="sidebar-brand d-flex align-items-center justify-content-center" href="admin.html">
        <div class="sidebar-brand-icon rotate-n-15">
            <i class="fas fa-laugh-wink"></i>
        </div>
        <div class="sidebar-brand-text mx-3">PQRS UFPS</div>
    </a>
    

    <!-- Divider -->
    <hr class="sidebar-divider">

    <div class="sidebar-heading">
        Herramientas
    </div>

    <li class="nav-item">
        <a class="nav-link" href="admin.html">
        <i class="fas fa-file-pdf"></i>
            <span>Listado de Usuarios </span></a>
    </li>

    <hr class="sidebar-divider d-none d-md-block">

    <li class="nav-item">
        <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseTwo"
            aria-expanded="true" aria-controls="collapseTwo">
            <i class="fas fa-fw fa-cog"></i>
            <span>PQRS</span>
        </a>
        <div id="collapseTwo" class="collapse" aria-labelledby="headingTwo" data-parent="#accordionSidebar">
            <div class="bg-white py-2 collapse-inner rounded">
                <h6 class="collapse-header">Administrar PQRS</h6>
                <a class="collapse-item" href="peticiones.html">Peticiones</a>
                <a class="collapse-item" href="quejasUsuarios.html">Quejas</a>
                <a class="collapse-item" href="reclamos.html">Reclamos</a>
                <a class="collapse-item" href="sugerencias.html">Sugerencias</a>
            </div>
        </div>
    </li>

    

    <!-- Nav Item - Pages Collapse Menu -->

    <li class="nav-item">
        <a class="nav-link" href="fqa.html">
        <i class="fas fa-fw fa-cog"></i>
        <span>Gestión FQA </span></a>
    </li>


    <hr class="sidebar-divider d-none d-md-block">

    
<!-- End of Sidebar -->

`;

let logoutModal = `
<div class="modal fade" id="logoutModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Cerrar sesión</h5>
                <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">×</span>
                </button>
            </div>
            <div class="modal-body">¿Estas seguro de cerrar sesión?</div>
            <div class="modal-footer">
                <button class="btn btn-secondary" type="button" data-dismiss="modal">Cancelar</button>
                <a class="btn btn-primary" href="#" onclick="logout()">Cerrar sesión</a>
            </div>
        </div>
    </div>
</div>

`;

let footer = `
    <footer class="sticky-footer bg-white">
        <div class="container my-auto">
            <div class="copyright text-center my-auto">
                <span>Copyright &copy; PQRS UFPS</span>
            </div>
        </div>
    </footer>
 `;

const logout = () => {
    localStorage.clear();
    window.location.href = "login.html";
}

const obtenerUsuarioLogueado = () => {
    const email = localStorage.getItem("email");
    console.log(email)

    if (email != null) {
        fetch("http://pqrs.us-east-1.elasticbeanstalk.com/usuario/encontrar/" + email, {
            headers: {
                Authorization: `Bearer ${token}`
            }
        })
            .then((response) => response.json())
            .then((data) => {

                const imgElement = document.getElementById('perfil');
                const nombre = document.getElementById('nombre');


                imgElement.src = base + data.usuario.imagen;
                nombre.innerText = data.usuario.nombre.split(" ")[0] + " " + data.usuario.apellido.split(" ")[0];

                localStorage.setItem("id_usuario", data.usuario.id_usuario)

            })


    } else {
        localStorage.clear();
        window.location.href = "index.html";
    }
};


const recientesRadicados = () => {
    fetch("http://pqrs.us-east-1.elasticbeanstalk.com/radicado/recientes", {
        headers: {
            Authorization: `Bearer ${token}`
        }
    })
        .then((response) => response.json())
        .then((data) => {
            console.log("ANDERSON")

            console.log(data)
            let notificaciones = document.getElementById("pqrs");

            if (data.error != null) {
                let noti = document.createElement("a");
                noti.classList.add('dropdown-item');
                noti.classList.add('d-flex');
                noti.classList.add('align-items-center');

                noti.innerHTML = `<div class="mr-3">
                                            <div class="icon-circle bg-primary">
                                                <i class="fas fa-file-alt text-white"></i>
                                            </div>
                                        </div>
                                        <div>
                                            <span class="font-weight-bold">No hay notificaciones</span>
                                        </div>`

                notificaciones.appendChild(noti);

            } else {
                for (let i = 0; i < 5; i++) {

                    let noti = document.createElement("a");
                    noti.classList.add('dropdown-item');
                    noti.classList.add('d-flex');
                    noti.classList.add('align-items-center');

                    noti.innerHTML = `<div class="mr-3">
                                            <div class="icon-circle bg-primary">
                                                <i class="fas fa-file-alt text-white"></i>
                                            </div>
                                        </div>
                                        <div>
                                            <div class="small text-gray-500">Hace algunas horas</div>
                                            <span class="font-weight-bold">${data.pqrs[i].titulo}</span>
                                        </div>
                                        <div class="ml-3">
                                            <span class="font-weight-bold">${data.pqrs[i].tipo.nombre}</span>
                                        </div>`

                    notificaciones.appendChild(noti);
                }
            }


        })
}


document.addEventListener("DOMContentLoaded", function () {

    if (localStorage.getItem("rol") == "ROLE_STUDENT") {
        window.location.href = "index.html"
    }
    document.getElementById("sidebar").innerHTML = sidebarTemplate;
    document.getElementById("navbar").innerHTML = navbarTemplate;
    document.getElementById("logout").innerHTML = logoutModal;
    document.getElementById("footer").innerHTML = footer;

    obtenerUsuarioLogueado();
    recientesRadicados();
});

