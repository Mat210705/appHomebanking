const app = Vue.createApp({
    data() {
        return {
          clients: [], 
          accounts: [],           
          aTerceros:false,
          aPropia:false,
          alert: false,
          ctaOrigen:"",
          ctaDestino:"",         
          ctaTerceros:"",
          monto:"",
          descripcion:"",




        }
    },
    created() {
        this.loadData();
      
    },

    methods: {
        loadData() {
            axios.get("/api/clients/current")
                .then(response => {
                    console.log(response);
                    this.clients = response.data;
                    this.accounts = response.data.accounts;                    
                })
        },
        realizarTransferencia(){
            axios.post('/api/clients/current/transaction',"amount="+this.monto+"&"+"description="+this.descripcion+"&"+"numberAccountOrigin="+this.ctaOrigen+"&"+"numberAccountDestini="+this.ctaDestino,{headers:{'content-type':'application/x-www-form-urlencoded'}})
            .then(res => swal(
                'Transferencia realizada con éxito!',
                 'Puedes chequear tu cuenta.',
                 'success',), setTimeout(() => {
                     location.reload();
                 }, 3000))
                           
            .catch(res => swal("Intente nuevamente","error"))
        },
        showModalATerceros(e) {
            console.log(e.target)
            this.aTerceros = true;
            this.aPropia = false;            
        },
        showModalCtaPropia(e) {
            console.log(e.target)
            this.aTerceros = false;
            this.aPropia= true;            
        },
        showModalAlert(e){
            console.log(e.target)
            this.alert = true;
        },
        showModalAlertB(e){
            console.log(e.target)
            this.alert = false;
        },
        


    },
    computed:{
        filtrarCuenta(){
            return this.accounts.filter(account => account.number != this.ctaOrigen);
        }
}





})
let consol = app.mount("#app")