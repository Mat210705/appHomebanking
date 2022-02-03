const app = Vue.createApp({
    data() {
        return {
            clients: [],
            card: [],            
            Debit: [],
            Credit: [],                      
            cardId: [],
            cardNumber: "",
            alert: false,



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
                    this.card = response.data.card;                    
                    this.Debit = this.card.filter(elemento => elemento.type === "DEBIT");
                    this.Credit = this.card.filter(elemento => elemento.type === "CREDIT");                       
                })
        },
       
        EliminarTarjeta(card){
            swal({
                title: '¿Desea dar de baja la tarjeta?',
                text: "Esta acción no se podrá revertir",
                icon: 'warning',                       
                button:'OK',
                dangerMode: true,
              })
                    .then((willDelete) => {
                        if (willDelete) {
                            axios.delete(`/api/cards/delete/?number=${this.cardNumber}`,{headers:{'content-type':'application/x-www-form-urlencoded'}})
                                .then(res => swal(
                                'Se realizó la baja con éxito!',
                                    'vaya a la sección "Crear tarjetas" para solicitar otra.',
                                    'success',))
                                .then(response => location.reload())
                                .catch(res => swal(res.response.data,"Intente nuevamente","error"))
                  
                }
              })
        },                             
        logOut(e){
            e.preventDefault() 
           axios.post('/api/logout')
           .then(response => window.location.href = ("/web/index.html"))
        },

        tarjetaVencida(tarjeta){
            let today = new Date();
            let expired = new Date(tarjeta.trhuDate);
                return today > expired 
        },
        showModalAlert(e){
            console.log(e.target)
            this.alert = true;
        },
        showModalAlertB(e){
            console.log(e.target)
            this.alert = false;
        },
    } 
       
           

        
        





    






})
let consol = app.mount("#app")




