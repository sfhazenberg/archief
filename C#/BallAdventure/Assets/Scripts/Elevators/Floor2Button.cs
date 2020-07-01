using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Floor2Button : MonoBehaviour {

    public Elevator Elevator;

    private void OnTriggerEnter(Collider other) {
        //Elevator.ToFloor2();
        Elevator.ToFloor(1);
    }
}
