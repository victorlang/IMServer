//created by zhang jian xin 2016 @IBM
package com.ibm.cloud.im.model.data.KVStoredao;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.ibm.cloud.im.model.data.KVStore.MailBody;
import com.ibm.cloud.im.server.service.infrastructure.MongoConnectionMgr;
import com.mongodb.DBObject;
import com.mongodb.WriteResult;


public abstract class BaseDao<T> {
	private Class<T> entityClass;

	@Autowired
	protected MongoConnectionMgr connectionMgr;
	
	protected MongoTemplate mongoTemplate;

	/**
	 * 通过反射获取子类确定的泛型类
	 */
	@SuppressWarnings("unchecked")
	public BaseDao() {
		Type genType = getClass().getGenericSuperclass();
		Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
		entityClass = (Class<T>) params[0];
	}
	public MongoTemplate getMongoAccessor()
	{
		if (mongoTemplate==null)
			mongoTemplate = connectionMgr.getMongoAccessor(entityClass.toString());
		return mongoTemplate;
	}

	public long count(Query query) {

		return getMongoAccessor().count(query, entityClass);
	}

	public List<T> find(Query query) {

		return getMongoAccessor().find(query, entityClass);
	}

	public T findOne(Query query) {

		return getMongoAccessor().findOne(query, entityClass);
	}

	public T findByID(Object id) {

		return getMongoAccessor().findById(id, entityClass);
	}

	public T findByID(int id, String dbname) {

		return getMongoAccessor().findById(id, entityClass, dbname);
	}

	public void insert(T obj) {
		getMongoAccessor().insert(obj);
	}
	public void insert(List<MailBody> objectsToSave)
	{
		MongoTemplate template = getMongoAccessor();
		String CollectionName = template.getCollectionName(entityClass);
		template.insertAll(objectsToSave);
	}

	public void delete(T obj) {
		getMongoAccessor().remove(obj);
	}

	public void deleteById(long id) {
		Query query = new Query(Criteria.where("_id").is(id));
		getMongoAccessor().remove(query, entityClass);
	}

	/**
	 * 
	 * @param query
	 * @param update
	 * @return the old object
	 */
	public T findAndModify(Query query, Update update) {

		return getMongoAccessor().findAndModify(query, update, entityClass);
	}
	
	public T findModifyReturnNew(Query query, Update update)
	{
		FindAndModifyOptions opt = new FindAndModifyOptions ();
		opt.returnNew(true);
		return getMongoAccessor().findAndModify(query, update, opt,entityClass);
	}

	public WriteResult updateFirst(Query query, Update update) {

		return getMongoAccessor().updateFirst(query, update, entityClass);

	}

	public void updateMulti(Query query, Update update) {

		getMongoAccessor().updateMulti(query, update, entityClass);

	}

	/**
	 * 
	 * @param obj
	 */
	public void save(T obj) {
		getMongoAccessor().save(obj);
	}

}
