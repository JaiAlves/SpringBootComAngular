create table usuario (idUsuario integer not null,
		      email	character varying(60) not null,	
		      nome	character varying(60),	
		      ativo 	boolean,
CONSTRAINT usuario_pkey PRIMARY KEY (idUsuario),
CONSTRAINT usuario_uk unique (email)
)
