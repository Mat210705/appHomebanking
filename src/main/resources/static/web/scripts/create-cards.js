const app = Vue.createApp({
    data() {
        return {
            card: [],
            clients: [],
            type:"",
            color:"",
            cardId: "",

        }
    },
    created() {        
        
    },
    methods: {
        crearTarjeta(e) {
            e.preventDefault()             
            axios.post('/api/clients/current/cards', `type=${this.type}&color=${this.color}`,{ headers: {'content-type':'application/x-www-form-urlencoded'}})
            .then(response => window.location.href = ("/web/cards.html"))
            .then(response =>{
                this.card = response.data.card;
            })
        
            
            },
            deleteCardConfirm() {
                axios.delete('/api/clients/current/cards/delete' + this.cardId)
                    .then(() => swal('Su tarjeta a sido borrada con exito'))
                    .then(() => location.reload())
    
                    .catch(err => {
                        swal(err)
                       
                        location.reload()
                    })
            },
            logOut(e){
                e.preventDefault() 
               axios.post('/api/logout')
               .then(response => window.location.href = ("/web/index.html"))
            },
         
        
        
    },





    
    
})
let consol = app.mount("#app")

        
