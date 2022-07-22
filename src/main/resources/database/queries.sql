SELECT concat(j.nome," - ", j.apelido) as jogador, p.gol, p.assistencia, p.defesa FROM sysfut.partida as p
inner join jogador as j on p.fk_idjogador = j.idjogador
order by gol desc;