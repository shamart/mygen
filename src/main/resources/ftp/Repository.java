package ${groupId}.${artifactId}.repository;

import ${groupId}.${artifactId}.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ${domainName}Repository extends JpaRepository<${domainName}, Long> {
}
