using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class elevator1ReturnTrigger : MonoBehaviour {

    public Elevator Elevator;
    public ElevatorDoorTrigger ElevatorDoorTrigger;

    private void OnTriggerEnter(Collider other)
    {
        if(other.gameObject.CompareTag("Player"))
        {
            //Elevator.StopMove();
            ElevatorDoorTrigger.EnableCollider();
        }
    }
}
