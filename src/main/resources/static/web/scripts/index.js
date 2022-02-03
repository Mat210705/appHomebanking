const app = Vue.createApp({
    data() {
        return {
            firstName: "",
            lastName: "",
           email:"",
           password:"",
           emailA:"",
           passwordA:"",
           showModalIniciarSesion:true,
           showModalRegistro: false,
           



        }
    },
    created() {
        
    },
    methods: {
        // loadData() {             
        //     axios.post('/api/login', `email=${this.email}&password=${this.password}`, { headers: {'content-type':'application/x-www-form-urlencoded'}})            
        //     .then(res => swal(
        //         'Buenos dias'`${this.firstName}`,
        //          'Usted a iniciado sesion con exito',
        //          'success'))                  
        //          .catch(res => swal("El usuario o contraseña no son correctos","Intente nuevamente","error"))
        //     },
        loadData() {
            axios.post('/api/login', `email=${this.email}&password=${this.password}`,{ headers: {'content-type':'application/x-www-form-urlencoded'}})
            .then(res => window.location.href = ("/web/accounts.html"))
            .catch(res => swal("Su Usuario y contraseña es Incorrecto","Intente nuevamente"))
                
        },
         crearCliente(e){
             e.preventDefault()            
             axios.post('/api/clients', `firstName=${this.firstName}&lastName=${this.lastName}&email=${this.email}&password=${this.password}`, { headers: {'content-type':'application/x-www-form-urlencoded'}})             
             .then(response => swal( 'Hola, '+`${this.firstName}`,
             'Su cuenta se a creado con exito.',
             'success'))
             .then(() => {
                axios.post('/api/login', `email=${this.email}&password=${this.password}`,{ headers: {'content-type':'application/x-www-form-urlencoded'}})
                .then(res => window.location.href = ("/web/accounts.html"))
             }) 
             
         },         
         registroCliente(e){
            console.log(e.target)
            this.showModalRegistro = true;
            this.showModalIniciarSesion = false;            
        },
        inicioCliente(e){
            console.log(e.target)
            this.showModalRegistro = false;
            this.showModalIniciarSesion = true;            
        },
        crearCuenta(){
            axios.post('/api/clients/current/accounts',{ headers: { 'content-type': 'application/x-www-form-urlencoded' } })            
            .then(response => response())
        },
        
    },





    
    
})
let consol = app.mount("#app")


        window.addEventListener('load', () => {
            document.getElementById('spinner').className ="d-none";
            document.getElementById('none').className ="";
        })