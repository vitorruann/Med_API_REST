CREATE TABLE patients(
    
    id bigint NOT NULL AUTO_INCREMENT,
    name varchar(100) NOT NULL,
    email varchar(100) NOT NULL UNIQUE,
    phone varchar(20) NOT NULL,
    cpf varchar(14) NOT NULL UNIQUE,
    public_area varchar(100) NOT NULL,
    neighborhood varchar(100) NOT NULL,
    cep varchar(9) NOT NULL,
    complement varchar(100),
    number varchar(20),
    uf char(2),
    city varchar(100) NOT NULL,

    PRIMARY KEY(id)

);