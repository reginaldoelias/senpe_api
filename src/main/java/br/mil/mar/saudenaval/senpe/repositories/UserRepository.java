package br.mil.mar.saudenaval.senpe.repositories;

import br.mil.mar.saudenaval.senpe.entities.User;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.*;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


public interface UserRepository extends JpaRepository<User,String>, JpaSpecificationExecutor<User> {

    UserDetails findByUsername(String username);

    default Optional<User> findByNipAndDate(String nip, LocalDate dataNascimento){
        Specification<User> specs = (root, query, criteriaBuilder) -> criteriaBuilder.conjunction();
        Specification<User> NipEqual = (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("username"),nip);
        Specification<User> dataEqual = (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("dataNascimento"),dataNascimento);
        specs = specs.and(NipEqual).and(dataEqual);
        return findOne(specs);
    }

    default Optional<User> findUserByUsername(String username){
        Specification<User> specs = (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("username"),username);
        return findOne(specs);
    }

    default Optional<User> findByCpf(String cpf){
        Specification<User> specs = (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("cpf"),cpf);
        return findOne(specs);
    }


    @Override
    List<User> findAll();

    @Modifying
    @NativeQuery(value = "UPDATE tb_user SET password = ?2 WHERE username = ?1")
    int updatePassword(String username, String password);

    @Modifying
    @NativeQuery(value = "UPDATE tb_user SET perfil = ?2 WHERE username = ?1")
    int updateProfile(String username, String perfil);

    @Modifying
    @Query(value = "UPDATE tb_user SET login_at = ?2, login_count = nextval('login_user_count') WHERE username = ?1" , nativeQuery = true)
    void setLastLogin(String username, LocalDateTime dataHora);
}
