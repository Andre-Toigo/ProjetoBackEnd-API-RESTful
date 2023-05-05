   package com.teste.primeiroexemplo.model;

    import java.time.LocalDate;
    import jakarta.persistence.Entity;
    import jakarta.persistence.GeneratedValue;
    import jakarta.persistence.GenerationType;
    import jakarta.persistence.Id;
    
    @Entity
    public class Cliente {
        
        //#region Atributos
        @Id 
        @GeneratedValue(strategy = GenerationType.AUTO)
        
        private Integer id;
    
        private String nome;
    
        private String cpf;
    
        private String email;
    
        private LocalDate dataNascimento;
        //#endregion

        //#region Getters e Setters
        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

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
