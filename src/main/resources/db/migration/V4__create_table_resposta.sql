create table resposta(
    id bigint not null auto_increment,
    mensagem varchar(300) not null,
    data_criacao timestamp not null default current_timestamp,
    topico_id bigint not null,
    autor_id bigint not null,
    primary key (id),
    foreign key (topico_id) references topico(id),
    foreign key (autor_id) references usuario(id)
)