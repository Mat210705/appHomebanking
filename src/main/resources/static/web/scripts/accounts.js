const app = Vue.createApp({
    data() {
        return {
            clients: [],
            accounts: [],            
            loan: [],
            loans: [],
            showModalTitular: true,
            showModalCuenta: false,
            showModalPrestamos: false,
            alert:false,
            type:"",
            accountNumber: "",
            
            



        }
    },
    created() {
        this.loadData();
    },
    methods: {
        loadData() {
            axios.get('/api/clients/current')
                .then(response => {
                    console.log(response);
                    this.clients = response.data;
                    this.accounts = response.data.accounts;
                    this.loan = response.data.loan;
                    console.log(this.loan);                    
                })
        },
        loadLoans(){
            axios.get("/api/loans")
            .then(response => {
                console.log(response);
                this.loans = response.data;                     
            })
        },
        
        showModalTitularA(e) {
            console.log(e.target)
            this.showModalTitular = true;
            this.showModalCuenta = false;
            this.showModalPrestamos = false;

        },
        showModalCuentaA(e) {
            console.log(e.target)
            this.showModalTitular = false;
            this.showModalCuenta = true;
            this.showModalPrestamos = false;

        },
        showModalPrestamosB(element) {
            console.log(element.target)
            this.showModalTitular = false;
            this.showModalCuenta = false;
            this.showModalPrestamos = true;
        },
        showModalAlert(e){
            console.log(e.target)
            this.alert = true;
        },
        showModalAlertB(e){
            console.log(e.target)
            this.alert = false;
        },

        logOut(e){
            e.preventDefault() 
           axios.post('/api/logout')
           .then(response => window.location.href = ("/web/index.html"))
        },
        getUrl(id) {
            return `./account.html?id=${id}`        
        },         
        crearCuenta(){
            axios.post('/api/clients/current/accounts',`type=${this.type}`,{ headers: { 'content-type': 'application/x-www-form-urlencoded' } })
            .then(res => swal(
                'Tu cuenta a sido creada con exito',
                 'Puedes chequear tu nueva cuenta.',
                 'success',), setTimeout(() => {
                  location.reload();
                }, 3000))                           
            .catch(res => swal("Intente nuevamente", "El error puede deberse a que usted ya tiene 3 cuentas comuniquese con su banco", "error",), setTimeout(() => {
                location.reload();
            }, 3000))                            
        },
        borrarCuenta(account) {
            swal({
                title: "Desea eliminar su cuenta?",
                text: "Esta acción no se podrá revertir",
                icon: "warning",
                buttons: 'OK',
                dangerMode: true,
            })
                .then((willDelete) => {
                    if (willDelete) {
                        axios.delete(`/api/accounts/delete?number=${this.accountNumber}`, { headers: { 'content-type': 'application/x-www-form-urlencoded' } })
                            .then(() => swal("The Account has been deleted", {
                                icon: "success",
                            }))
                            .then(() => location.reload())
                            .catch(err => console.log(err))
                    }
                });
        },
        


    }







})
let consol = app.mount("#app")

window.addEventListener('load', () => {
    document.getElementById('spinner').className ="d-none";
    document.getElementById('cards-group').className ="";
})