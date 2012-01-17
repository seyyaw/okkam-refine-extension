/*
 * Copyright 2010 University of Trento, Italy
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package it.okkam.rdf2okkam.ens.client;

import java.util.Arrays;
import java.util.List;
import java.util.Vector;

import javax.xml.bind.JAXBException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.iaccess.accesscontrollayer.IAccessManager;
import org.okkam.client.data.Entity;
import org.okkam.client.exception.OkkamClientException;
import org.okkam.client.util.XMLEntityConverter;
import org.okkam.client.wsclient.IOkkamCredential;
import org.okkam.core.data.api.MatchingCandidate;
import org.okkam.core.data.api.SemanticType;
import org.okkam.core.ws.OkkamAuthorizationException;
import org.okkam.core.ws.data.EntityValidationReport;
import org.okkam.core.ws.data.NewEntityResultClient;
import org.okkam.core.ws.secured.OkkamCoreException;
import org.okkam.proxyclient.security.iaccess.AuthorizationManager;
import org.okkam.proxyclient.security.iaccess.IAccessClientOkkam;

public class EnsClient {

	private final static Log log = LogFactory.getLog(EnsClient.class);
	// configuration data
	private String okkamEndPoint = "";
	private String username = "";
	private String password = "";
	private String dataConfFolder = "";
	private String authKey = "";

	// okkam proxy
	private IAccessClientOkkam proxy = null;

	public EnsClient(IOkkamCredential okkamCredential)
			throws OkkamClientException {
		this.username = okkamCredential.getUsername();
		this.password = okkamCredential.getPassword();
		this.dataConfFolder = okkamCredential.getDataFolder();
		this.okkamEndPoint = okkamCredential.getOkkamEndPoint();
		this.authKey = "asdfghjkl";
		this.init();
	}

	public EnsClient() throws OkkamClientException {
		this.init();

	}

	/**
	 * Load the entity from Okkam that has the input oid as identifier.
	 * 
	 * @param oid
	 *            the okkam identifier of the {@link Entity} to load
	 * @return an object that contains the {@link Entity} loaded
	 * @throws {@link OkkamCoreException},
	 *             {@link OkkamClientException}
	 */
	public Entity getEntity(String oid) throws OkkamClientException,
			OkkamCoreException {
		// check input okkam id
		if (oid != null) {
			if (oid.compareTo("") != 0) {
				if (authKey != null) {
					if (authKey.compareTo("") != 0) {
						// call the private method that implement the service
						return this.getEntityImp(oid, authKey);
					} else {
						log
								.error("Impossible to run the service: the input authkey is void");
						throw new IllegalArgumentException(
								"Impossible to run the service: the input authkey is void");
					}
				} else {
					log
							.error("Impossible to run the service: the input authkey is null");
					throw new IllegalArgumentException(
							"Impossible to run the service: the input authkey is null");
				}
			} else {
				log
						.error("Impossible to run the service: the input id is void");
				throw new IllegalArgumentException(
						"Impossible to run the service: the input id is void");
			}
		} else {
			log.error("Impossible to run the service: the input id is null");
			throw new IllegalArgumentException(
					"Impossible to run the service: the input id is null");
		}
	}

	/**
	 * Load the list of entities from Okkam that have the input set of OkkamID
	 * as identifiers.
	 * 
	 * @param oids
	 *            the list of okkam identifiers of the entities to load
	 * @return a list of {@link Entity} object that contains the Entities loaded
	 * @throws {@link OkkamCoreException},
	 *             {@link OkkamClientException},
	 * 
	 */
	public Vector<Entity> getEntities(List<String> oids)
			throws OkkamClientException, OkkamCoreException {
		// check if the input set is null
		if (oids != null) {
			// call the private implementation of the service
			if (authKey != null) {
				if (authKey.compareTo("") != 0) {
					return this.getEntitiesImp(oids, authKey);
				} else {
					log
							.error("Impossible to run the service: the input authkey is void");
					throw new IllegalArgumentException(
							"Impossible to run the service: the input authkey is void");
				}
			} else {
				log
						.error("Impossible to run the service: the input authkey is null");
				throw new IllegalArgumentException(
						"Impossible to run the service: the input authkey is null");
			}
		} else {
			log
					.error("Impossible to run the service: the input id set is null");
			throw new IllegalArgumentException(
					"Impossible to run the service: the input id set is null");
		}
	}

	/**
	 * Get the list of identifiers (like emails, internal db key, unique
	 * identifiers from other website/services) that the input entity (OkkamID)
	 * has in the Okkam Storage.
	 * 
	 * @param oid
	 *            the okkam identifier
	 * @return a List of Alternative Identifiers as Strings
	 * @throws {@link OkkamCoreException},
	 *             {@link OkkamClientException},
	 */
	public List<String> getAlternativeIds(String oid)
			throws OkkamClientException, OkkamCoreException {
		// check input okkam id
		if (oid != null) {
			if (oid.compareTo("") != 0) {
				// call the private method that implement the service
				if (authKey != null) {
					if (authKey.compareTo("") != 0) {
						return this.getAlternativeIdsImp(oid, authKey);
					} else {
						log
								.error("Impossible to run the service: the input authkey is void");
						throw new IllegalArgumentException(
								"Impossible to run the service: the input authkey is void");
					}
				} else {
					log
							.error("Impossible to run the service: the input authkey is null");
					throw new IllegalArgumentException(
							"Impossible to run the service: the input authkey is null");
				}
			} else {
				log
						.error("Impossible to run the service: the input id is void");
				throw new IllegalArgumentException(
						"Impossible to run the service: the input id is void");
			}
		} else {
			log.error("Impossible to run the service: the input id is null");
			throw new IllegalArgumentException(
					"Impossible to run the service: the input id is null");
		}
	}

	/**
	 * Get the list of Okkam IDs that has the input alternative id (an email
	 * address or an id from another domain) in its list of alternative ids.
	 * 
	 * @param alternativeId
	 *            the alternative id to check
	 * @return a List of Okkam Uris that have the input id as alternativeId
	 * @throws {@link OkkamCoreException},
	 *             {@link OkkamClientException}
	 */
	public List<String> getOidsByAlternativeId(String alternativeId)
			throws OkkamClientException, OkkamCoreException {
		// check the input alternative id before call the implementation of the
		// service
		if (alternativeId != null) {
			if (alternativeId.compareTo("") != 0) {
				if (authKey != null) {
					if (authKey.compareTo("") != 0) {
						// call the private method that implement the service
						return this.getOidsByAlternativeIdImp(alternativeId,
								authKey);
					} else {
						log
								.error("Impossible to run the service: the input authkey is void");
						throw new IllegalArgumentException(
								"Impossible to run the service: the input authkey is void");
					}
				} else {
					log
							.error("Impossible to run the service: the input authkey is null");
					throw new IllegalArgumentException(
							"Impossible to run the service: the input authkey is null");
				}
			} else {
				log
						.error("Impossible to run the service: the input alternative id is void");
				throw new IllegalArgumentException(
						"Impossible to run the service: the input alternative id is void");
			}
		} else {
			log
					.error("Impossible to run the service: the input alternative id is null");
			throw new IllegalArgumentException(
					"Impossible to run the service: the input alternative id is null");
		}
	}

	/**
	 * Search an entity in Okkam. For more info about the query language to use
	 * in this service visit www.okkam.org
	 * 
	 * @param stringQuery
	 *            the input query
	 * @return a list of {@link MatchingCandidate} (oid + {@link Entity} +
	 *         similarity measure)
	 * @throws {@link OkkamCoreException},
	 *             {@link OkkamClientException}
	 */
	public List<MatchingCandidate> findEntity(String stringQuery)
			throws OkkamClientException, OkkamCoreException {
		// check input query
		if (stringQuery != null) {
			if (stringQuery.compareTo("") != 0) {
				if (authKey != null) {
					if (authKey.compareTo("") != 0) {
						// call the private implementation of the method
						return this.findEntityImp(stringQuery, authKey);
					} else {
						log
								.error("Impossible to run the service: the input authkey is void");
						throw new IllegalArgumentException(
								"Impossible to run the service: the input authkey is void");
					}
				} else {
					log
							.error("Impossible to run the service: the input authkey is null");
					throw new IllegalArgumentException(
							"Impossible to run the service: the input authkey is null");
				}
			} else {
				log
						.error("Impossible to run the service: the input query is void");
				throw new IllegalArgumentException(
						"Impossible to run the service: the input query is void");
			}
		} else {
			log.error("Impossible to run the service: the input query is null");
			throw new IllegalArgumentException(
					"Impossible to run the service: the input query is null");
		}
	}

	/**
	 * Load an empty entity structure for the input entity type. In the result
	 * entity you can find a set of default attributes with only the name of the
	 * attribute without the values
	 * 
	 * @param type
	 *            an okkam entity type. Use the static fields of
	 *            {@link SemanticType}
	 * @throws {@link OkkamCoreException},
	 *             {@link OkkamClientException}
	 * 
	 */
	public Entity getTypeTemplate(String type) throws OkkamClientException,
			OkkamCoreException {
		if (type != null) {
			if (type.compareTo("") != 0) {
				return this.getTypeTemplateImp(type);

			} else {
				log
						.error("Impossible to run the service: the input type is void");
				throw new IllegalArgumentException(
						"Impossible to run the service: the input type is void");
			}
		} else {
			log.error("Impossible to run the service: the input type is null");
			throw new IllegalArgumentException(
					"Impossible to run the service: the input type is null");
		}
	}

	public void logSelectedEntity(String oid, String query)
			throws OkkamAuthorizationException {
		if (oid == null)
			throw new IllegalArgumentException("Okkam id is null");
		if (oid.compareTo("") == 0)
			throw new IllegalArgumentException("Okkam id is void");
		if (query == null)
			throw new IllegalArgumentException("Query is null");
		if (query.compareTo("") == 0)
			throw new IllegalArgumentException("Query is void");
		proxy.logSelectedEntity(oid, query);

	}

	/**
	 * Lock the entity with the input okkam id. The entity cannot be used by
	 * other processes until the user unlock it or the session expires.
	 * 
	 * @param oid
	 *            the okkam id of the entity to lock
	 * @return a ticket that is requested in services like "update", "merge",
	 *         etc.
	 * @throws {@link OkkamCoreException},
	 *             {@link OkkamClientException}
	 */
	public String lockEntity(String oid) throws IllegalArgumentException,
			OkkamClientException, OkkamCoreException {
		if (oid != null) {
			if (oid.compareTo("") != 0) {

				try {
					return proxy.lockEntity(oid);
				} catch (IllegalArgumentException exc) {
					log.error(exc.getMessage());
					throw new IllegalArgumentException(exc.getMessage());
				} catch (Exception exc) {
					log.error(exc.getMessage());
					throw new OkkamClientException(exc.getMessage());
				} catch (Throwable t) {
					log.error(t.getMessage());
					throw new OkkamClientException(t.getMessage());
				}
			} else {
				log
						.error("Impossible to lock the Entity, the input okkam id is void");
				throw new IllegalArgumentException(
						"Impossible to lock the Entity, the input okkam id is void");
			}
		} else {
			log
					.error("Impossible to lock the Entity, the input okkam id is null");
			throw new IllegalArgumentException(
					"Impossible to lock the Entity, the input okkam id is null");
		}
	}

	/**
	 * Validate the input entity
	 * 
	 * @param e
	 *            the entity to validate
	 * @param ignoreDuplicates
	 * @return a validation report with all the information about the input
	 *         entity
	 * @throws {@link OkkamCoreException},
	 *             {@link OkkamClientException}
	 */
	public EntityValidationReport validateEntity(Entity e,
			boolean ignoreDuplicates) throws IllegalArgumentException,
			OkkamClientException, org.okkam.core.ws.OkkamCoreException {
		if (e != null) {
			try {
				XMLEntityConverter converter = new XMLEntityConverter();
				String entityXML = converter.entityToXml(e);

				return proxy.validateEntity(entityXML, ignoreDuplicates);
			} catch (IllegalArgumentException exc) {
				log.error(e+exc.getMessage());
				throw new IllegalArgumentException(exc.getMessage());
			}

			catch (Exception exc) {
				log.error(e+exc.getMessage());
				throw new OkkamClientException(exc.getMessage());
			} catch (Throwable t) {
				log.error(e+t.getMessage());
				throw new OkkamClientException(t.getMessage());
			}

		} else {
			log
					.error(e+"Impossible to lock the Entity, the input okkam id is null");
			throw new IllegalArgumentException(
					"Impossible to lock the Entity, the input okkam id is null");
		}

	}

	/**
	 * Lock a list of entities
	 * 
	 * @param oids
	 * @return a list of tickets
	 * @throws {@link OkkamCoreException},
	 *             {@link OkkamClientException}
	 */
	public List<String> lockEntities(List<String> oids)
			throws IllegalArgumentException, OkkamClientException,
			OkkamCoreException {
		if (oids != null) {

			try {
				String[] oidsArray = oids.toArray(new String[0]);
				String[] result = proxy.lockEntities(oidsArray);
				return Arrays.asList(result);
			} catch (IllegalArgumentException exc) {
				log.error(exc.getMessage());
				throw new IllegalArgumentException(exc.getMessage());
			} catch (Exception exc) {
				log.error(exc.getMessage());
				throw new OkkamClientException(exc.getMessage());
			} catch (Throwable t) {
				log.error("Impossible to lock entities");
				throw new OkkamClientException("Impossible to lock entities, "
						+ t.getMessage());
			}
		} else {
			log
					.error("Impossible to lock the Entity, the input okkam ids is null");
			throw new IllegalArgumentException(
					"Impossible to lock the Entity, the input okkam ids is null");
		}
	}

	/**
	 * Unlock an Entity
	 * 
	 * @param oid
	 *            the okkam id of the entity to unlock
	 * @param ticket
	 *            the ticket returned from the server after the lock process
	 * @throws {@link OkkamCoreException},
	 *             {@link OkkamClientException}
	 */
	public void unlockEntity(String oid, String ticket)
			throws IllegalArgumentException, OkkamClientException,
			OkkamCoreException {
		if (oid != null) {
			if (ticket != null) {

				try {
					proxy.unlockEntity(oid, ticket);

				} catch (IllegalArgumentException exc) {
					log.error(exc.getMessage());
					throw new IllegalArgumentException(exc.getMessage());
				} catch (Exception exc) {
					log.error(exc.getMessage());
					throw new OkkamClientException(exc.getMessage());
				} catch (Throwable t) {
					log.error("Impossible to unlock the entity");
					throw new OkkamClientException(
							"Impossible to unlock the entity, "
									+ t.getMessage());
				}
			} else {
				log
						.error("Impossible to unlock the Entity, the input ticket is null");
				throw new IllegalArgumentException(
						"Impossible to unlock the Entity, the input ticket is null");
			}

		} else {
			log
					.error("Impossible to unlock the Entity, the input certificate is null");
			throw new IllegalArgumentException(
					"Impossible to unlock the Entity, the input certificate is null");
		}
	}

	/**
	 * Create a new Entity in Okkam
	 * 
	 * @param certificate
	 *            the certificated returned after the entity validation process
	 * @return the okkam id of the new entity
	 * @throws {@link OkkamCoreException},
	 *             {@link OkkamClientException}
	 */
	public String createNewEntity(String certificate)
			throws OkkamClientException, IllegalArgumentException,
			OkkamCoreException {
		if (certificate != null) {

			try {
				String result = proxy.createNewEntity(certificate);
				return result;
			} catch (IllegalArgumentException exc) {
				log.error(exc.getMessage());
				throw new IllegalArgumentException(exc.getMessage());
			} catch (Exception exc) {
				log.error(exc.getMessage());
				throw new OkkamClientException(exc.getMessage());
			} catch (Throwable t) {
				log.error("Impossible to create the entity");
				throw new OkkamClientException(
						"Impossible to create the entity, " + t.getMessage());
			}
		} else {
			log
					.error("Impossible to create the Entity, the input certificate is null");
			throw new IllegalArgumentException(
					"Impossible to create the Entity, the input certificate is null");
		}
	}

	public String createNewEntityOnBehalf(String certificate, byte[] x509UserCert)
			throws OkkamClientException, IllegalArgumentException,
			OkkamCoreException {
		if (certificate != null) {

			try {
				String result = proxy.createNewEntityOnBehalf(certificate,
						x509UserCert);
				return result;
			} catch (IllegalArgumentException exc) {
				log.error(exc.getMessage());
				throw new IllegalArgumentException(exc.getMessage());
			} catch (Exception exc) {
				log.error(exc.getMessage());
				throw new OkkamClientException(exc.getMessage());
			} catch (Throwable t) {
				log.error("Impossible to create the entity");
				throw new OkkamClientException(
						"Impossible to create the entity, " + t.getMessage());
			}
		} else {
			log
					.error("Impossible to create the Entity, the input certificate is null");
			throw new IllegalArgumentException(
					"Impossible to create the Entity, the input certificate is null");
		}
	}

	/**
	 * delete the entity with the input id
	 * 
	 * @param oid
	 * @param ticket
	 *            the ticked returned after the lock process
	 * @throws {@link OkkamCoreException},
	 *             {@link OkkamClientException}
	 */
	public void deleteEntity(String oid, String ticket)
			throws IllegalArgumentException, OkkamClientException,
			OkkamCoreException {
		if (oid != null) {
			if (ticket != null) {

				try {
					proxy.deleteEntity(oid, ticket);
				} catch (IllegalArgumentException exc) {
					log.error(exc.getMessage());
					throw new IllegalArgumentException(exc.getMessage());
				} catch (Exception exc) {
					log.error(exc.getMessage());
					throw new OkkamClientException(exc.getMessage());
				} catch (Throwable t) {
					log.error("Impossible to delete the entity");
					throw new OkkamClientException(
							"Impossible to delete the entity, "
									+ t.getMessage());
				}
			} else {
				log
						.error("Impossible to delete the Entity, the input ticket is null");
				throw new IllegalArgumentException(
						"Impossible to delete the Entity, the input ticket is null");
			}
		} else {
			log
					.error("Impossible to delete the Entity, the input certificate is null");
			throw new IllegalArgumentException(
					"Impossible to delete the Entity, the input certificate is null");
		}
	}

	public void deleteEntityOnBehalf(String oid, String ticket, byte[] x509UserCert)
			throws IllegalArgumentException, OkkamClientException,
			OkkamCoreException {
		if (oid != null) {
			if (ticket != null) {

				try {
					proxy.deleteEntityOnBehalf(oid, ticket, x509UserCert);
				} catch (IllegalArgumentException exc) {
					log.error(exc.getMessage());
					throw new IllegalArgumentException(exc.getMessage());
				} catch (Exception exc) {
					log.error(exc.getMessage());
					throw new OkkamClientException(exc.getMessage());
				} catch (Throwable t) {
					log.error("Impossible to delete the entity");
					throw new OkkamClientException(
							"Impossible to delete the entity, "
									+ t.getMessage());
				}
			} else {
				log
						.error("Impossible to delete the Entity, the input ticket is null");
				throw new IllegalArgumentException(
						"Impossible to delete the Entity, the input ticket is null");
			}
		} else {
			log
					.error("Impossible to delete the Entity, the input certificate is null");
			throw new IllegalArgumentException(
					"Impossible to delete the Entity, the input certificate is null");
		}
	}

	/**
	 * Merge a list of entities into one
	 * 
	 * @param oids
	 *            the list of the entities ids to merge
	 * @param mergedEntity
	 *            the result of the merge
	 * @param tickets
	 *            the list of the ticket returned from the server after all the
	 *            lock processes
	 * @return *
	 * @throws {@link OkkamCoreException},
	 *             {@link OkkamClientException}
	 */
	public NewEntityResultClient mergeEntities(String[] oids,
			Entity mergedEntity, String[] tickets) throws OkkamClientException,
			OkkamCoreException {
		if (oids != null) {
			if (tickets != null) {
				if (mergedEntity != null) {

					try {
						XMLEntityConverter converter = new XMLEntityConverter();
						String entityXML = converter.entityToXml(mergedEntity);
						return proxy.mergeEntities(oids, entityXML, tickets);
					} catch (IllegalArgumentException exc) {
						log.error(exc.getMessage());
						throw new IllegalArgumentException(exc.getMessage());
					} catch (Exception exc) {
						log.error(exc.getMessage());
						throw new OkkamClientException(exc.getMessage());
					} catch (Throwable t) {
						log.error("Impossible to delete the entity");
						throw new OkkamClientException(
								"Impossible to delete the entity, "
										+ t.getMessage());
					}
				} else {
					log
							.error("Impossible to merge the entities, the input ticket is null");
					throw new IllegalArgumentException(
							"Impossible to merge the entities, the input ticket is null");
				}
			} else {
				log
						.error("Impossible to merge the entities, the input ticket is null");
				throw new IllegalArgumentException(
						"Impossible to merge the entities, the input ticket is null");
			}
		} else {
			log
					.error("Impossible to merge the entities, the input certificate is null");
			throw new IllegalArgumentException(
					"Impossible to merge the entities, the input certificate is null");
		}
	}

	public NewEntityResultClient mergeEntitiesOnBehalf(String[] oids,
			Entity mergedEntity, String[] tickets, byte[] x509UserCert)
			throws OkkamClientException, OkkamCoreException {
		if (oids != null) {
			if (tickets != null) {
				if (mergedEntity != null) {

					try {
						XMLEntityConverter converter = new XMLEntityConverter();
						String entityXML = converter.entityToXml(mergedEntity);
						return proxy.mergeEntitiesOnBehalf(oids, entityXML,
								tickets, x509UserCert);
					} catch (IllegalArgumentException exc) {
						log.error(exc.getMessage());
						throw new IllegalArgumentException(exc.getMessage());
					} catch (Exception exc) {
						log.error(exc.getMessage());
						throw new OkkamClientException(exc.getMessage());
					} catch (Throwable t) {
						log.error("Impossible to delete the entity");
						throw new OkkamClientException(
								"Impossible to delete the entity, "
										+ t.getMessage());
					}
				} else {
					log
							.error("Impossible to merge the entities, the input ticket is null");
					throw new IllegalArgumentException(
							"Impossible to merge the entities, the input ticket is null");
				}
			} else {
				log
						.error("Impossible to merge the entities, the input ticket is null");
				throw new IllegalArgumentException(
						"Impossible to merge the entities, the input ticket is null");
			}
		} else {
			log
					.error("Impossible to merge the entities, the input certificate is null");
			throw new IllegalArgumentException(
					"Impossible to merge the entities, the input certificate is null");
		}
	}

	/**
	 * Split one entity into more sub-entities
	 * 
	 * @param oid
	 *            the okkam id of the entity to split
	 * @param splitEntities
	 *            the list of the new sub-profiles
	 * @param ticket
	 *            the ticket for the splitted lock entity
	 * @return a list of newEntityResultClient objects, one for each sub-entity
	 * @throws {@link OkkamCoreException},
	 *             {@link OkkamClientException}
	 */
	public NewEntityResultClient[] splitEntity(String oid,
			List<Entity> splitEntities, String ticket)
			throws OkkamClientException, IllegalArgumentException,
			OkkamCoreException {

		try {
			XMLEntityConverter converter = new XMLEntityConverter();
			String[] results = new String[splitEntities.size()];
			for (int i = 0; i < splitEntities.size(); i++) {
				Entity entity = splitEntities.get(i);
				String entityXML = converter.entityToXml(entity);
				results[i] = entityXML;
			}

			return proxy.splitEntity(oid, results, ticket);
		} catch (IllegalArgumentException exc) {
			log.error(exc.getMessage());
			throw new IllegalArgumentException(exc.getMessage());
		} catch (Exception exc) {
			log.error(exc.getMessage());
			throw new OkkamClientException(exc.getMessage());
		} catch (Throwable t) {
			log.error("Impossible to delete the entity");
			throw new OkkamClientException("Impossible to delete the entity, "
					+ t.getMessage());
		}
	}

	public NewEntityResultClient[] splitEntityOnBehalf(String oid,
			List<Entity> splitEntities, String ticket, byte[] x509UserCert)
			throws OkkamClientException, IllegalArgumentException,
			OkkamCoreException {

		try {
			XMLEntityConverter converter = new XMLEntityConverter();
			String[] results = new String[splitEntities.size()];
			for (int i = 0; i < splitEntities.size(); i++) {
				Entity entity = splitEntities.get(i);
				String entityXML = converter.entityToXml(entity);
				results[i] = entityXML;
			}

			return proxy
					.splitEntityOnBehalf(oid, results, ticket, x509UserCert);
		} catch (IllegalArgumentException exc) {
			log.error(exc.getMessage());
			throw new IllegalArgumentException(exc.getMessage());
		} catch (Exception exc) {
			log.error(exc.getMessage());
			throw new OkkamClientException(exc.getMessage());
		} catch (Throwable t) {
			log.error("Impossible to delete the entity");
			throw new OkkamClientException("Impossible to delete the entity, "
					+ t.getMessage());
		}
	}

	/**
	 * Update an entity
	 * 
	 * @param ticket
	 *            the lock ticket
	 * @param certificate
	 *            the validation certificate
	 * @throws {@link OkkamCoreException},
	 *             {@link OkkamClientException}
	 */
	public void updateEntity(String ticket, String certificate)
			throws IllegalArgumentException, OkkamClientException,
			OkkamCoreException {
		if (certificate != null) {
			if (ticket != null) {

				try {
					proxy.updateEntity(ticket, certificate);
				} catch (IllegalArgumentException exc) {
					log.error(exc.getMessage());
					throw new IllegalArgumentException(exc.getMessage());
				} catch (Exception exc) {
					log.error(exc.getMessage());
					throw new OkkamClientException(exc.getMessage());
				} catch (Throwable t) {
					log.error("Impossible to create the entity");
					throw new OkkamClientException(
							"Impossible to create the entity, "
									+ t.getMessage());
				}
			} else {
				log
						.error("Impossible to update the Entity, the input ticket is null");
				throw new IllegalArgumentException(
						"Impossible to update the Entity, the input ticket is null");
			}

		} else {
			log
					.error("Impossible to update the Entity, the input certificate is null");
			throw new IllegalArgumentException(
					"Impossible to update the Entity, the input certificate is null");
		}
	}

	public void updateEntityOnBehalf(String ticket, String certificate,
			byte[] x509UserCert) throws IllegalArgumentException,
			OkkamClientException, OkkamCoreException {
		if (certificate != null) {
			if (ticket != null) {

				try {
					proxy.updateEntityOnBehalf(ticket, certificate,
							x509UserCert);
				} catch (IllegalArgumentException exc) {
					log.error(exc.getMessage());
					throw new IllegalArgumentException(exc.getMessage());
				} catch (Exception exc) {
					log.error(exc.getMessage());
					throw new OkkamClientException(exc.getMessage());
				} catch (Throwable t) {
					log.error("Impossible to create the entity");
					throw new OkkamClientException(
							"Impossible to create the entity, "
									+ t.getMessage());
				}
			} else {
				log
						.error("Impossible to update the Entity, the input ticket is null");
				throw new IllegalArgumentException(
						"Impossible to update the Entity, the input ticket is null");
			}

		} else {
			log
					.error("Impossible to update the Entity, the input certificate is null");
			throw new IllegalArgumentException(
					"Impossible to update the Entity, the input certificate is null");
		}
	}

	public byte[] getServerCertificate() throws OkkamClientException {
		try {
			return proxy.getServerCertificate();
		} catch (Throwable t) {
			log.error("Impossible to get the server certificate, "
					+ t.getMessage());
			throw new OkkamClientException(
					"Impossible to get the server certificate");
		}
	}

	/**
	 * // *********************** private *****************************
	 */
	private Entity getEntityImp(String oid, String authKey)
			throws OkkamClientException, OkkamCoreException {
		try {
			String serviceResult = proxy.getEntity(oid);
			// convert // xml // -> // Entity
			XMLEntityConverter converter = new XMLEntityConverter();
			Entity e = converter.xmlToEntity(serviceResult);
			return e;
		} catch (JAXBException exc) {
			log
					.error("Impossible to get the entity, convertion xml -> Entity exception, "
							+ exc.getMessage());
			throw new OkkamClientException(
					"Impossible to get the entity, convertion xml -> Entity exception, "
							+ exc.getMessage());
		} catch (Exception exc) {
			log.error("Impossible to get the entity, client exception, "
					+ exc.getMessage());
			throw new OkkamClientException(exc.getMessage());
		} catch (Throwable t) {
			log.error("Impossible to get the entity, client exception, "
					+ t.getMessage());
			throw new OkkamClientException(t.getMessage());
		}
	}

	private Vector<Entity> getEntitiesImp(List<String> oids, String authKey)
			throws OkkamClientException, OkkamCoreException {
		try {
			// load the proxy

			XMLEntityConverter converter = new XMLEntityConverter();
			// convert the list -> array

			String[] clientIds = oids.toArray(new String[0]);

			String[] clientResult = proxy.getEntities(clientIds);
			// convert the results
			Vector<Entity> vector = new Vector<Entity>(clientResult.length);
			for (int i = 0; i < clientResult.length; i++) {
				String xml = clientResult[i];
				Entity e = converter.xmlToEntity(xml);
				vector.add(e);
			}
			return vector;
		} catch (JAXBException exc) {
			log
					.error("Impossible to get the entities, convertion xml -> Entity exception, "
							+ exc.getMessage());
			throw new OkkamClientException(
					"Impossible to get the entities, convertion xml -> Entity exception, "
							+ exc.getMessage());
		}

		catch (Exception exc) {
			log.error("Impossible to get the entities, core exception, "
					+ exc.getMessage());
			throw new OkkamClientException(exc.getMessage());
		} catch (Throwable t) {
			log.error("Impossible to get the entities, client exception, "
					+ t.getMessage());
			throw new OkkamClientException(t.getMessage());
		}
	}

	private List<String> getAlternativeIdsImp(String oid, String authKey)
			throws OkkamClientException, OkkamCoreException {
		try {
			String[] clientResult = proxy.getAlternativeIds(oid);
			List<String> results = Arrays.asList(clientResult);
			return results;
		} catch (Exception exc) {
			log
					.error("Impossible to get the alternative ids, client exception, "
							+ exc.getMessage());
			throw new OkkamClientException(exc.getMessage());
		} catch (Throwable t) {
			log
					.error("Impossible to get the alternative ids, client exception, "
							+ t.getMessage());
			throw new OkkamClientException(t.getMessage());
		}
	}

	private List<String> getOidsByAlternativeIdImp(String alternativeId,
			String authKey) throws OkkamClientException, OkkamCoreException {
		try {
			String[] clientResult = proxy.getOidsByAlternativeId(alternativeId);
			List<String> results = Arrays.asList(clientResult);
			return results;
		} catch (Exception exc) {
			log.error("Impossible to get the okkam ids, client exception, "
					+ exc.getMessage());
			throw new OkkamClientException(exc.getMessage());
		} catch (Throwable t) {
			log.error("Impossible to get the okkam ids, client exception, "
					+ t.getMessage());
			throw new OkkamClientException(t.getMessage());
		}
	}

	private List<MatchingCandidate> findEntityImp(String stringQuery,
			String authKey) throws OkkamClientException, OkkamCoreException {

		try {
			MatchingCandidate[] serverResult = proxy.findEntity(stringQuery);
			// MatchingCandidate mc = new MatchingCandidate();

			// convert server result to client result
			List<MatchingCandidate> mcList = Arrays.asList(serverResult);
			return mcList;
		} catch (Exception exc) {
			log
					.error("Impossible to run the search service, client exception, "
							+ exc.getMessage());
			throw new OkkamClientException(exc.getMessage());
		} catch (Throwable t) {
			log
					.error("Impossible to run the search service, client exception, "
							+ t.getMessage());
			throw new OkkamClientException(t.getMessage());
		}

	}

	private Entity getTypeTemplateImp(String type) throws OkkamClientException,
			OkkamCoreException {
		try {
			String xml = proxy.getTypeTemplate(type);
			XMLEntityConverter converter = new XMLEntityConverter();
			Entity e = converter.xmlToEntity(xml);
			return e;
		} catch (JAXBException exc) {
			log
					.error("Impossible to get type template, convertion xml -> Entity exception, "
							+ exc.getMessage());
			throw new OkkamClientException(
					"Impossible to get type template, convertion xml -> Entity exception, "
							+ exc.getMessage());
		} catch (Exception exc) {
			log.error("Impossible to get type template, client exception, "
					+ exc.getMessage());
			throw new OkkamClientException(exc.getMessage());
		} catch (Throwable t) {
			log.error("Impossible to get type template, client exception, "
					+ t.getMessage());
			throw new OkkamClientException(t.getMessage());
		}
	}

	private void init() throws OkkamClientException {
		try {

			AuthorizationManager authManager = AuthorizationManager
					.getInstance();
			authManager.initIAccess(username, password, dataConfFolder);

			// At this point, all the certificates must be loaded

			IAccessManager iAccessManager = authManager.getIAccessManager();

			proxy = new IAccessClientOkkam(iAccessManager, okkamEndPoint);

		} catch (Throwable t) {
			log.error(t.getMessage());
			t.printStackTrace();
			throw new OkkamClientException(
					"Impossible to load the okkam proxy, no service available.");
		}
	}
}
