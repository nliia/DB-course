SELECT
  'name' || md5(random() :: TEXT), 'surname' || md5(random() :: TEXT), address
FROM generate_series(1,1000) address;


SELECT (SELECT address FROM users WHERE id=gen_random_uuid()) from generate_series(1,5000);