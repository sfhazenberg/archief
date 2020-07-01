using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class WallController : MonoBehaviour {

    public float movementSpeed = 1;
    public Transform wallDestination;

    private Vector3 destination;
    private GameObject[] getCoins;
    private int numberOfCoins;
            
    void Start()
    {
        getCoins = GameObject.FindGameObjectsWithTag("Coin");   //gets number of active coins
        numberOfCoins = getCoins.Length;                        //sets int to number of active coins
        destination = wallDestination.position;
    }

    void Update ()
    {
        if (PlayerController.coinCount >= numberOfCoins)
        {
            Vector3 pWall = destination;
            if (transform.position != pWall) //gently moves wall to preset destination (pWall)
            {
                transform.position = Vector3.MoveTowards(transform.position, pWall, movementSpeed * Time.deltaTime);
            }
            else if (transform.position == pWall) //unloads wall when it has reached pWall
            {
                gameObject.SetActive(false);
            }
        }
	}
}
