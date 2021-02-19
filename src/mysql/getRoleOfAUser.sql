SELECT name 
FROM user_role 
JOIN role 
ON user_role.role_id = role.id; 