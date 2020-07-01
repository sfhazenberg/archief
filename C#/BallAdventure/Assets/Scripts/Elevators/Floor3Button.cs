using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Floor3Button : MonoBehaviour {

    public Elevator Elevator;

    private void OnTriggerEnter(Collider other) {
        Elevator.ToFloor(2);
    }
}
