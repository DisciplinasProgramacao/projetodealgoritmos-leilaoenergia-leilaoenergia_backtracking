## FPAA  - LEILÃO DE ENERGIA

AVALIAÇÃO "TUDO JUNTO"

_Individual_:
Apresentação: 4 pontos

_Nota em Grupo:_
  - Relatório: 	 5/6
  
  Faltou apenas comparar os resultados entre os algoritmos (BT x AG x PD, por exemplo) e verificar se vale a pena usar algoritmos que gastam mais tempo etc.

Implementação: 7/10
  PD sem ordenar as interessadas.

  DC: como falado na apresentação, não me parece uma DC efetiva. A verificação ("combinação") é feita antes da chamada recursiva. A chamada recursiva é feita a partir de um "máximo lucro", porém os lances já são ordenados antes do início do procedimento. É feita uma verificação de "energia restante", o que não faz sentido no problema: aquele lance pode ser combinado em outras partes. 
