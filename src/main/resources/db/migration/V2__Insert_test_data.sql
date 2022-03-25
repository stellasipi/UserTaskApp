INSERT INTO "user"
VALUES (NEXTVAL('user_sequence'), 'Jared', 'Smith', 'jared.smith');

INSERT INTO task
VALUES (NEXTVAL('task_sequence'), '2021-06-09 14:25:00', 'Test task 1', 'Test1', 'DONE',
(SELECT id FROM "user" WHERE username = 'jared.smith'));