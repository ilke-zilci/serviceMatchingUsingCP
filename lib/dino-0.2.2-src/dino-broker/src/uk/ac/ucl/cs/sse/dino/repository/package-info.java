/**
 * Implementation of the Repository component. The main implementation class is
 * <code>ServiceInformationRepositoryImpl</code>. This uses the class <code>ServiceMatchRepository</code>
 * to record matches between requirements and service implementations. The repository also stores
 * quality of service statistics using the <code>ServiceStats</code> class. The repository maintains
 * a directory of service descriptions and an index file. The class <code>RepositoryIndexParser</code>
 * is responsible for parsing the index file. 
 */
package uk.ac.ucl.cs.sse.dino.repository;