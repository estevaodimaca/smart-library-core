-- Livros
INSERT INTO books (id, title, author, isbn, available_copies, created_at) VALUES ('123e4567-e89b-12d3-a456-426614174000', 'The Pragmatic Programmer', 'Andrew Hunt', '978-0201616224', 5, CURRENT_TIMESTAMP) ON CONFLICT (id) DO NOTHING;
INSERT INTO books (id, title, author, isbn, available_copies, created_at) VALUES ('123e4567-e89b-12d3-a456-426614174001', 'Clean Architecture', 'Robert C. Martin', '978-0134494166', 2, CURRENT_TIMESTAMP) ON CONFLICT (id) DO NOTHING;
INSERT INTO books (id, title, author, isbn, available_copies, created_at) VALUES ('123e4567-e89b-12d3-a456-426614174002', 'Design Patterns', 'Erich Gamma', '978-0201633610', 0, CURRENT_TIMESTAMP) ON CONFLICT (id) DO NOTHING;

-- Perfis
INSERT INTO profiles (id, name, created_at) VALUES ('223e4567-e89b-12d3-a456-426614174001', 'SECURITY_ADMIN', CURRENT_TIMESTAMP) ON CONFLICT (id) DO NOTHING;
INSERT INTO profiles (id, name, created_at) VALUES ('223e4567-e89b-12d3-a456-426614174002', 'LIBRARIAN', CURRENT_TIMESTAMP) ON CONFLICT (id) DO NOTHING;
INSERT INTO profiles (id, name, created_at) VALUES ('223e4567-e89b-12d3-a456-426614174003', 'USER_COMUNITY', CURRENT_TIMESTAMP) ON CONFLICT (id) DO NOTHING;

-- Transações e Permissões
INSERT INTO transactions (id, name, route, icon, created_at) VALUES ('323e4567-e89b-12d3-a456-426614174001', 'Dashboard', '/dashboard', 'dashboard', CURRENT_TIMESTAMP) ON CONFLICT (id) DO NOTHING;
INSERT INTO transactions (id, name, route, icon, created_at) VALUES ('323e4567-e89b-12d3-a456-426614174002', 'My Loans', '/my-loans', 'menu_book', CURRENT_TIMESTAMP) ON CONFLICT (id) DO NOTHING;
INSERT INTO transactions (id, name, route, icon, created_at) VALUES ('323e4567-e89b-12d3-a456-426614174003', 'Issue Loan', '/issue-loan', 'bookmark_add', CURRENT_TIMESTAMP) ON CONFLICT (id) DO NOTHING;

INSERT INTO profile_transactions (profile_id, transaction_id) VALUES ('223e4567-e89b-12d3-a456-426614174001', '323e4567-e89b-12d3-a456-426614174001') ON CONFLICT DO NOTHING;
INSERT INTO profile_transactions (profile_id, transaction_id) VALUES ('223e4567-e89b-12d3-a456-426614174001', '323e4567-e89b-12d3-a456-426614174002') ON CONFLICT DO NOTHING;
INSERT INTO profile_transactions (profile_id, transaction_id) VALUES ('223e4567-e89b-12d3-a456-426614174001', '323e4567-e89b-12d3-a456-426614174003') ON CONFLICT DO NOTHING;

-- Usuários
-- Senhas (exemplo "123456" com BCrypt: $2a$10$D/R38q5C72381yQo2f/LauPqDDEd6K1d.6Z69Wk4fJvP2V7N1cZVy)
INSERT INTO users (id, first_name, last_name, tax_id, email, password, active, created_at) VALUES ('423e4567-e89b-12d3-a456-426614174001', 'Estevão', 'Dimaca', '105633920', 'estevaodimaca@gmail.com', '$2a$10$D/R38q5C72381yQo2f/LauPqDDEd6K1d.6Z69Wk4fJvP2V7N1cZVy', true, CURRENT_TIMESTAMP) ON CONFLICT (id) DO NOTHING;
INSERT INTO users (id, first_name, last_name, tax_id, email, password, active, created_at) VALUES ('423e4567-e89b-12d3-a456-426614174002', 'Estevão', 'Dimaka', '105633921', 'estevaodimaka@gmail.com', '$2a$10$D/R38q5C72381yQo2f/LauPqDDEd6K1d.6Z69Wk4fJvP2V7N1cZVy', true, CURRENT_TIMESTAMP) ON CONFLICT (id) DO NOTHING;
INSERT INTO users (id, first_name, last_name, tax_id, email, password, active, created_at) VALUES ('423e4567-e89b-12d3-a456-426614174003', 'Hipólito', 'Dimaka', '105633922', 'hipolitoestevaodimaca@gmail.com', '$2a$10$D/R38q5C72381yQo2f/LauPqDDEd6K1d.6Z69Wk4fJvP2V7N1cZVy', true, CURRENT_TIMESTAMP) ON CONFLICT (id) DO NOTHING;

-- Associação de Perfis aos Usuários
INSERT INTO user_profiles (user_id, profile_id) VALUES ('423e4567-e89b-12d3-a456-426614174001', '223e4567-e89b-12d3-a456-426614174001') ON CONFLICT DO NOTHING;
INSERT INTO user_profiles (user_id, profile_id) VALUES ('423e4567-e89b-12d3-a456-426614174002', '223e4567-e89b-12d3-a456-426614174002') ON CONFLICT DO NOTHING;
INSERT INTO user_profiles (user_id, profile_id) VALUES ('423e4567-e89b-12d3-a456-426614174003', '223e4567-e89b-12d3-a456-426614174003') ON CONFLICT DO NOTHING;
