/**
 * Implements the OWL-S Service Matcher component. This component consist of a functional matchmaker,
 * <code>OwlsMatchmaker</code> and a quality of service matcher, <code>OwlsQosMatchmaker</code>.
 * The QoS matcher delegates to the functional matchmaker to provide functional matchmaking and then
 * further restricts the set of matching implementations.
 */
package uk.ac.ucl.cs.sse.dino.matchmaker.owls;