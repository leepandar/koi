package ${package}.${moduleName}.repository;

import ext.mybatisplus.com.koi.common.db.SuperMapper;
import ${package}.${moduleName}.domain.entity.${ClassName};
import org.springframework.stereotype.Repository;

/**
* ${table.comment!}接口层
*
* @author ${author}
* @since ${date}
*/

@Repository
public interface ${ClassName}Mapper extends SuperMapper<${ClassName}> {

}
