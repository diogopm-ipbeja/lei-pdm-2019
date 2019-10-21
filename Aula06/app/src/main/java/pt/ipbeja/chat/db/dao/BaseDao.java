package pt.ipbeja.chat.db.dao;

import androidx.room.Insert;

/**
 * Interface base para os DAOs. Esta interface NÃO É um DAO e não deve ser anotada como tal mas
 * devem anotar-se os métodos.
 * Cada DAO deve estender (se for ele próprio uma interface) ou implementar (se for uma classe
 * abstracta) este BaseDao
 * Desta forma evita-se a repetição da definição dos métodos insert/update/delete em cada DAO
 * Não é obrigatório mas pode ser muito conveniente quando temos muitos DAOs.
 * @param <ENTITY> O tipo da Entity
 */
interface BaseDao<ENTITY> {

    @Insert
    long insert(ENTITY t);
    // TODO: Anotar os restantes métodos

    int update(ENTITY t);

    int delete(ENTITY t);

}
