INSERT INTO `spanners`.`users`
(`username`,
`password`,
`enabled`)
VALUES
('admin',
'password',
true);

INSERT INTO `spanners`.`users`
(`username`,
`password`,
`enabled`)
VALUES
('smith',
'password',
true);

INSERT INTO `spanners`.`users`
(`username`,
`password`,
`enabled`)
VALUES
('jones',
'password',
true);

INSERT INTO `spanners`.`authorities`
(`username`,
`authority`)
VALUES
('admin',
'ROLE_VIEWER');

INSERT INTO `spanners`.`authorities`
(`username`,
`authority`)
VALUES
('admin',
'ROLE_EDITOR');

INSERT INTO `spanners`.`authorities`
(`username`,
`authority`)
VALUES
('admin',
'ROLE_ADMIN');

INSERT INTO `spanners`.`authorities`
(`username`,
`authority`)
VALUES
('smith',
'ROLE_VIEWER');

INSERT INTO `spanners`.`authorities`
(`username`,
`authority`)
VALUES
('smith',
'ROLE_EDITOR');

INSERT INTO `spanners`.`authorities`
(`username`,
`authority`)
VALUES
('jones',
'ROLE_VIEWER');

INSERT INTO `spanners`.`authorities`
(`username`,
`authority`)
VALUES
('jones',
'ROLE_EDITOR');

