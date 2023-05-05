package com.teste.primeiroexemplo.view.model;


    import java.time.LocalDate;
      
    
    public class ClienteRequest {
        
        //#region Atributos
                   
        private String nome;
    
        private String cpf;
    
        private String email;
    
        private LocalDate dataNascimento;
        //#endregion

        //#region Getters e Setters
       
        public String getNome() {
            return nome;
        }

        public void setNome(String nome) {
            this.nome = nome;
        }

        public String getCpf() {
            return cpf;
        }

        public void setCpf(String cpf) {
            this.cpf = cpf;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public LocalDate getDataNascimento() {
            return dataNascimento;
        }

        public void setDataNascimento(LocalDate dataNascimento) {
            this.dataNascimento = dataNascimento;
        }
         //#endregion
       
}
