using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Respawn : MonoBehaviour {

    public PlayerController PlayerController;
    public Elevator Elevator;

    private void OnTriggerEnter(Collider other) {
        if(other.gameObject.CompareTag("Player")) {
            PlayerController.Respawning();
            Elevator.ToFloor(0);
        }
    }
}
