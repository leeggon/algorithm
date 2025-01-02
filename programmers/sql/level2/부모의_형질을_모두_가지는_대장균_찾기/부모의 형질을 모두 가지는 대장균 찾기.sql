SELECT C.ID AS ID, C.GENOTYPE AS GENOTYPE, P.GENOTYPE AS PARENT_GENOTYPE
    FROM ECOLI_DATA AS C
    JOIN ECOLI_DATA AS P
      ON C.PARENT_ID = P.ID
   WHERE P.GENOTYPE & C.GENOTYPE = P.GENOTYPE
ORDER BY ID;