<?php

namespace LogistiqueBundle\Repository;

/**
 * busRepository
 *
 * This class was generated by the Doctrine ORM. Add your own custom
 * repository methods below.
 */
class busRepository extends \Doctrine\ORM\EntityRepository
{
    public function getbusbyNbrplace()
    {
        $query=$this->getEntityManager()->createQuery("SELECT m FROM LogistiqueBundle:bus m ORDER BY m.nbrPlace ASC");
        return $query->getResult();
    }
}
