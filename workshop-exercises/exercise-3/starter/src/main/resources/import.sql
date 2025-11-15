-- Sample data for Task Management System with priorities
INSERT INTO Task(id, title, description, completed, priority, createdAt) VALUES (1, 'Learn Quarkus', 'Complete all workshop exercises', false, 'HIGH', CURRENT_TIMESTAMP);
INSERT INTO Task(id, title, description, completed, priority, createdAt) VALUES (2, 'Setup Development Environment', 'Install Java, Maven, and IDE', true, 'HIGH', CURRENT_TIMESTAMP);
INSERT INTO Task(id, title, description, completed, priority, createdAt) VALUES (3, 'Build REST API', 'Create CRUD endpoints for tasks', false, 'MEDIUM', CURRENT_TIMESTAMP);
INSERT INTO Task(id, title, description, completed, priority, createdAt) VALUES (4, 'Add Database Integration', 'Integrate Hibernate ORM with Panache', false, 'MEDIUM', CURRENT_TIMESTAMP);
INSERT INTO Task(id, title, description, completed, priority, createdAt) VALUES (5, 'Write Tests', 'Create comprehensive unit tests', true, 'LOW', CURRENT_TIMESTAMP);
INSERT INTO Task(id, title, description, completed, priority, createdAt) VALUES (6, 'Add Configuration', 'Implement configuration management', false, 'MEDIUM', CURRENT_TIMESTAMP);
INSERT INTO Task(id, title, description, completed, priority, createdAt) VALUES (7, 'Deploy to Production', 'Deploy application to cloud', false, 'LOW', CURRENT_TIMESTAMP);
