const formeElement = document.getElementById("formm");
formeElement.addEventListener("submit", (event)=> {
    event.preventDefault();

    let email = document.getElementById("email").value;
    let password = document.getElementById("password").value;

    let login = {email:email, password:password};
    let loginJSON = JSON.stringify(login);
    
    fetch(`http://localhost:3000/login`, {
        method: "POST",
        body: loginJSON
    })
    .then(Response => Response.text())
    .then((data) => {
        
        console.log(data) 
    })
})

