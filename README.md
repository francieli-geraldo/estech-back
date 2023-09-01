embaralhamento base de paciente 

update patient 
set name = replace(replace(replace(replace(replace(lower(name), 'a', 's'), 'e', 'r'), 'i', 'k'), 'o', 'p'), 'u', 'y')
, email = 'yourmail@mail.com'
,phone = concat('00099', cast(floor(rand()*91111111) + 99999999 as char(9))),
birth_date = '1991-01-01'

